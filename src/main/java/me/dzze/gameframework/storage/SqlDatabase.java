package me.dzze.gameframework.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class SqlDatabase extends AbstractSqlDatabase {
    public void createTables() {
        try (final PreparedStatement statement = this
                .getConnection()
                .prepareStatement("CREATE TABLE IF NOT EXISTS points (NAME VARCHAR(100),UUID VARCHAR(100),POINTS INT(100),PRIMARY KEY (NAME))")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CompletableFuture<Void> createPlayer(String name, UUID uuid) {
        return CompletableFuture.runAsync(() -> {
            if (!this.exists(uuid).join()) {
                try (final PreparedStatement statement = this
                        .getConnection().prepareStatement("INSERT IGNORE INTO points (NAME,UUID) VALUES (?,?)")) {
                    statement.setString(1, name);
                    statement.setString(2, uuid.toString());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CompletableFuture<Boolean> exists(UUID uuid) {
        return this.makeFuture(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("SELECT * FROM points WHERE UUID=?")) {
                statement.setString(1, uuid.toString());
                try (final ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public CompletableFuture<Void> addPoints(UUID uuid, int points) {
        return this.setPoints(uuid, this.getPoints(uuid).join() + points);
    }

    public CompletableFuture<Void> setPoints(UUID uuid, int points) {
        return CompletableFuture.runAsync(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("UPDATE points SET POINTS=? WHERE UUID=?")) {
                statement.setInt(1, points);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<Void> resetPoints(UUID uuid) {
        return CompletableFuture.runAsync(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("UPDATE points SET POINTS=? WHERE UUID=?")) {
                statement.setInt(1, 0);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<Integer> getPoints(UUID uuid) {
        return this.makeFuture(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("SELECT POINTS FROM points WHERE UUID=?")) {
                statement.setString(1, uuid.toString());
                try (final ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        return result.getInt("POINTS");
                    }
                }
            }
            return 0;
        });
    }

    public CompletableFuture<Void> emptyTable() {
        return CompletableFuture.runAsync(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("TRUNCATE points")) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public CompletableFuture<Void> removeTable(UUID uuid) {
        return CompletableFuture.runAsync(() -> {
            try (final PreparedStatement statement = this
                    .getConnection().prepareStatement("DELETE FROM points WHERE UUID=?")) {
                statement.setString(1, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private <T> CompletableFuture<T> makeFuture(Callable<T> callable) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        try {
            future.complete(callable.call());
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }
}

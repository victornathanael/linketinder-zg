package linketinder.zg.db.factory

import linketinder.zg.db.PostgreSQLConnection

class ConnectionProviderFactory {
    static IConnectionProvider createConnectionProvider(DatabaseType databaseType) {
        switch (databaseType) {
            case "POSTGRE":
                return new PostgreSQLConnection();
            default:
                throw new IllegalArgumentException("Unsupported database type: " + databaseType);
        }
    }
}

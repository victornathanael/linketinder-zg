package linketinder.zg.db.factory

import java.sql.Connection


interface IConnectionProvider {
    Connection connect();

    void disconnect();
}


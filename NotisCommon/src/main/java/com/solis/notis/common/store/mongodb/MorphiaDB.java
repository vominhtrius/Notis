package com.solis.notis.common.store.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.MapperOptions;

/**
 *
 * @author
 */
public class MorphiaDB {

    private static final String PACKAGE_MODEL = "com.solis.facebook.dto";

    private Morphia morphia;
    private Datastore datastore;

    public MorphiaDB(String connectURL, String dbName) {
        try {
            morphia = new Morphia();
            MapperOptions options = morphia.getMapper().getOptions();
            options.setStoreEmpties(true);
            options.setStoreNulls(true);
            
            morphia.mapPackage(PACKAGE_MODEL);
            datastore = morphia.createDatastore(new MongoClient(new MongoClientURI(connectURL)), dbName);
            datastore.ensureIndexes();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Datastore getDatastore() {
        return datastore;
    }
}

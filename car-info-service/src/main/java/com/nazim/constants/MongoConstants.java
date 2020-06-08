package com.nazim.constants;

/**
 * Static helper class with Mongo constants.
 */
public class MongoConstants {

    private MongoConstants() {
    }

    public static final String ID_FIELD = "_id";
    public static final String VERSION_FIELD = "version";
    public static final String MODIFIED_AT_FIELD = "modifiedAt";
    public static final String AVG_RATING_FIELD = "avgRating";

    public static final String COLLECTION_RESOURCE_NAME = "infos";
    public static final String COLLECTION_NAME_PREFIX_ENV_KEY = "collection.name.prefix";
    public static final String COLLECTION_NAME_DELIMITER = "--";
}

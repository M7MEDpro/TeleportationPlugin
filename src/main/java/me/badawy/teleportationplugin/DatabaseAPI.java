package me.badawy.teleportationplugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseAPI {
    public static  MongoClient client = MongoClients.create("mongodb://localhost:27017");
    public static  MongoDatabase database = client.getDatabase("MyDB");
    public static  MongoCollection<Document> teleportationcollection = database.getCollection("Teleportation");



}

package nh.mongodb.playground;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
public class Main {

  private final MongoClient mongoClient;
  private  MongoDatabase db;
  private MongoCollection<Document> coll;

  private Main() {
    mongoClient = new MongoClient("localhost", 27017);
  }

  public Main use(String dbName) {
    this.db = mongoClient.getDatabase(dbName);
    return this;
  }

  public Main dropDb() {
    this.db.drop();
    return this;
  }

  public Main useCollection(String collName) {
    this.coll = db.getCollection(collName);
    return this;
  }

  public Main createUniqueIndex(String fieldName) {
    Document document = new Document(fieldName, 1);
    coll.createIndex(document, new IndexOptions().unique(true));
    return this;
  }

  public Main insertDocument(Document document) {
    this.coll.insertOne(document);
    return this;
  }

  public Main updateDocument(String filterField, Object filterValue, Document newValue) {
    final Bson criteria = eq(filterField, filterValue);
    this.coll.updateOne(criteria, newValue, new UpdateOptions().upsert(true));
    return this;
  }

  public FindIterable<Document> find() {
    return this.coll.find().sort(Sorts.descending("visits")).limit(3);
  }

  private static void newDb() {
    Main main = new Main();
    main.use("jplayground");
//    main.dropDb();

    main.useCollection("visits").createUniqueIndex("productId");
    main.insertDocument(new Document().append("productId", 1).append("visits", 200));
    main.insertDocument(new Document().append("productId", 2).append("visits", 200));
    main.insertDocument(new Document().append("productId", 3).append("visits", 400));
  }



  public static void main(String[] args) {

    Main main = new Main();
    main.use("jplayground");
//    main.dropDb();

    main.useCollection("visits");
    main.updateDocument("productId", 7, new Document("$inc", new Document("visits", 1)));

    main.find().iterator().forEachRemaining((Document document) -> System.out.println(document.toJson()));
  }
}

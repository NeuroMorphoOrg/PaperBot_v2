/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.neuromorpho.paperbot.search.repository;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.neuromorpho.paperbot.search.model.KeyWord;
import org.neuromorpho.paperbot.search.model.Portal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KeyWordRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    MongoCollection<KeyWord> collection;

    @Autowired
    public KeyWordRepository(
            @Value("${spring.data.mongodb.database}") String DBNAME,
            @Value("${spring.data.mongodb.collection.keyword}") String COLLECTION_NAME) {

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase(DBNAME).withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection(COLLECTION_NAME, KeyWord.class);

    }
    

    public List<KeyWord> findExecuted(Boolean executed){
        Bson match = Filters.eq("executed", executed);
        FindIterable<KeyWord> resultList = collection.find(match);
        return resultList.into(new ArrayList<>());
    }

    public void update(ObjectId id, String field, Object value){
        Bson match = Filters.eq("_id", id);
        collection.updateOne(match, Updates.set(field, value));
    }

    public void updateAll(String field, Object value){
        collection.updateMany(new Document(), Updates.set(field, value));
    }

    public void saveAll(List<KeyWord> keyWordList){
        collection.insertMany(keyWordList);
    }

    public List<KeyWord> findAll(){
        FindIterable<KeyWord> resultList = collection.find(new Document());
        return resultList.into(new ArrayList<>());
    }
   

}

package org.neuromorpho.paperbot.metadata.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.neuromorpho.paperbot.metadata.model.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Repository
public class MetadataRepository {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    MongoCollection<Metadata> collection;

    @Autowired
    public MetadataRepository(
            @Value("${spring.data.mongodb.database}") String DBNAME,
            @Value("${spring.data.mongodb.collection}") String COLLECTION_NAME) {
        
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase(DBNAME).withCodecRegistry(pojoCodecRegistry);
        collection = database.getCollection(COLLECTION_NAME, Metadata.class);

    }

    
    public List<Metadata> findByType(String type){

        Bson match = Filters.eq("type", type);
        FindIterable<Metadata> resultList = collection.find(match);
        return resultList.into(new ArrayList<>());
        
    }

    public List<Metadata> findAll(){
        FindIterable<Metadata> resultList = collection.find();
        return resultList.into(new ArrayList<>());

    }
    

}

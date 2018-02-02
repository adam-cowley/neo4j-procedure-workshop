package org.neo4j.procedures;

import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.harness.junit.Neo4jRule;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest
{

    @Rule
    public Neo4jRule neo4j = new Neo4jRule()
            .withFunction( HelloWorld.class );

    @Test
    public void shouldGreetWorld() {
        GraphDatabaseService db = neo4j.getGraphDatabaseService();

        try ( Transaction tx = db.beginTx() ) {
            String name = "World";

            Map<String, Object> params = new HashMap<>(  );
            params.put("name", name);

            Result result = db.execute( "RETURN functions.hello({name}) as greeting", params);

            String greeting = (String) result.next().get("greeting");

            assertEquals( "Hello, "+ name, greeting );
        }
    }

}

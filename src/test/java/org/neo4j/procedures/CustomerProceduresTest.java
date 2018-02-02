package org.neo4j.procedures;

import org.junit.Rule;
import org.junit.Test;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.harness.junit.Neo4jRule;

import static org.junit.Assert.assertEquals;

public class CustomerProceduresTest
{

    @Rule
    public Neo4jRule neo4j = new Neo4jRule()
            .withProcedure( CustomerProcedures.class );

    @Test
    public void shouldMountMyProcedures() throws Throwable {
        GraphDatabaseService db = neo4j.getGraphDatabaseService();

        try ( Transaction tx = db.beginTx() ) {
            Result res = db.execute("CALL customers.create('Test') YIELD node RETURN node");

            Node node = (Node) res.next().get("node");

            assertEquals(node.getProperty("name"), "Test");
        }
    }

}

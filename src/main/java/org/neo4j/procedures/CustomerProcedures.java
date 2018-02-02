package org.neo4j.procedures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Mode;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

public class CustomerProcedures
{

    @Context
    public GraphDatabaseService db;

    @Context
    public Log log;

    public static Label Customer = Label.label( "Customer" );

    @Procedure( name = "customers.create", mode = Mode.WRITE )
    @Description( "customers.create(name) | Create a new Customer node" )
    public Stream<NodeResult> createCustomer( @Name( "name" ) String name ) {
        List<NodeResult> output = new ArrayList<>();

        try ( Transaction tx = db.beginTx() )  {
            Node node = db.createNode(Customer);

            node.setProperty("name", name);

            output.add( new NodeResult(node) );

            log.debug( "Creating Customer with Node ID " + node.getId() );

            tx.success();
        }

        return output.stream();
    }


    public static class NodeResult {
        public Node node;

        public NodeResult(Node node) {
            this.node = node;
        }
    }
}

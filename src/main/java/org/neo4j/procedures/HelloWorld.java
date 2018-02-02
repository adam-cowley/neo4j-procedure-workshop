package org.neo4j.procedures;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

public class HelloWorld
{

    @Context
    public GraphDatabaseService db;

    @UserFunction( name = "functions.hello")
    @Description( "hello(world) - Say hello!" )
    public String hello(@Name("world") String world) {
        return String.format("Hello, %s", world);
    }
}

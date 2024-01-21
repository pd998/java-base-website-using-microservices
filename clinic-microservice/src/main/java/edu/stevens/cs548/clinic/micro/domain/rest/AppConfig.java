package edu.stevens.cs548.clinic.micro.domain.rest;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.gson.JsonGsonFeature;
import org.glassfish.jersey.server.ResourceConfig;


// TODO
@DataSourceDefinition(
		name="java:global/jdbc/cs548",
		className="org.postgresql.ds.PGSimpleDataSource",
		user="${ENV=DATABASE_USERNAME}",
		password="${ENV=DATABASE_PASSWORD}",
		databaseName="${ENV=DATABASE}",
		serverName="${ENV=DATABASE_HOST}",
		portNumber=5432)
@ApplicationPath("/")
public class AppConfig extends ResourceConfig {

	public AppConfig() {
		packages("edu.stevens.cs548.clinic.micro.domain.rest").register(JsonGsonFeature.class);
	}

}

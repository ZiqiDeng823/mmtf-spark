package edu.sdsc.mmtf.spark.demos;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import edu.sdsc.mmtf.spark.io.MmtfReader;

/**
 * This example demonstrates how to read a list of PDB entries from a Hadoop Sequence File.
 * 
 * @author Peter Rose
 *
 */
public class Demo1d {

	public static void main(String[] args) {

	    if (args.length != 1) {
	        System.err.println("Usage: " + Demo1d.class.getSimpleName() + " <hadoop sequence file>");
	        System.exit(1);
	    }
	    
	    SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(Demo1d.class.getSimpleName());
	    JavaSparkContext sc = new JavaSparkContext(conf);
		 
		 
	    List<String> pdbIds = Arrays.asList("1STP","4HHB","1EP4");
	    
	    MmtfReader
	    		.readSequenceFile(args[0], pdbIds, sc) // read a set of structure from an MMTF hadoop sequence file
	    		.keys() // extract the keys (PDB IDs)
	    		.foreach(key -> System.out.println(key)); // print the keys (using a lambda expression)
	    
	    sc.close();
	}

}
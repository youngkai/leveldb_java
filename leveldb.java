package demo1;

import org.iq80.leveldb.*;

import static org.fusesource.leveldbjni.JniDBFactory.*;

import java.io.*;

public class leveldb {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		//Putting, Getting, and Deleting key/values.
		Options op = new Options();
		op.createIfMissing(true);
		DB db = factory.open(new File("/home/youngk/leveldb_data"), op);
		db.put(bytes("Tampa"), bytes("rocks"));
		String value = asString(db.get(bytes("Tampa")));
		System.out.println(value);
		
		
		//Performing Batch/Bulk/Atomic Updates.
		WriteBatch batch = db.createWriteBatch();
		try {
		  batch.delete(bytes("Denver"));
		  batch.put(bytes("Tampa"), bytes("green"));
		  batch.put(bytes("London"), bytes("red"));
		  db.write(batch); 
		} finally {
		  // Make sure you close the batch to avoid resource leaks.
		  batch.close();
		}
		
		
		//遍历
		DBIterator iterator = db.iterator();
		try {
		  for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
		    String key = asString(iterator.peekNext().getKey());
		    String value1 = asString(iterator.peekNext().getValue());
		    System.out.println(key+" = "+value1);
		  }
		} finally {
		  // Make sure you close the iterator to avoid resource leaks.
		  iterator.close();
		}
		
		
		//Working against a Snapshot view of the Database.

//		ReadOptions ro = new ReadOptions();
//		ro.snapshot(db.getSnapshot());
//		try {
//
//		  // All read operations will now use the same 
//		  // consistent view of the data.
//		  ... = db.iterator(ro);
//		  ... = db.get(bytes("Tampa"), ro);
//
//		} finally {
//		  // Make sure you close the snapshot to avoid resource leaks.
//		  ro.snapshot().close();
//		}
		
//		DBComparator comparator = new DBComparator(){
//		    public int compare(byte[] key1, byte[] key2) {
//		        return new String(key1).compareTo(new String(key2));
//		    }
//		    public String name() {
//		        return "simple";
//		    }
//		    public byte[] findShortestSeparator(byte[] start, byte[] limit) {
//		        return start;
//		    }
//		    public byte[] findShortSuccessor(byte[] key) {
//		        return key;
//		    }
//		};
//		Options options = new Options();
//		options.comparator(comparator);
//		DB db = factory.open(new File("example"), options);
		
		
//		Disabling Compression
//
//		Options options = new Options();
//		options.compressionType(CompressionType.NONE);
//		DB db = factory.open(new File("example"), options);
//		Configuring the Cache
//
//		Options options = new Options();
//		options.cacheSize(100 * 1048576); // 100MB cache
//		DB db = factory.open(new File("example"), options);
//		Getting approximate sizes.
//
//		long[] sizes = db.getApproximateSizes(new Range(bytes("a"), bytes("k")), new Range(bytes("k"), bytes("z")));
//		System.out.println("Size: "+sizes[0]+", "+sizes[1]);
//		Getting database status.
//
//		String stats = db.getProperty("leveldb.stats");
//		System.out.println(stats);
//		Getting informational log messages.
//
//		Logger logger = new Logger() {
//		  public void log(String message) {
//		    System.out.println(message);
//		  }
//		};
//		Options options = new Options();
//		options.logger(logger);
//		DB db = factory.open(new File("example"), options);
//		Destroying a database.
//
//		Options options = new Options();
//		factory.destroy(new File("example"), options);
//		Repairing a database.
//
//		Options options = new Options();
//		factory.repair(new File("example"), options);
//		Using a memory pool to make native memory allocations more efficient:
//
//		JniDBFactory.pushMemoryPool(1024 * 512);
//		try {
//		    // .. work with the DB in here, 
//		} finally {
//		    JniDBFactory.popMemoryPool();
//		}
		
	}

}

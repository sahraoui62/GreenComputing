package OPL_Map_Reduce.opl_map_reduce;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Anagrams {

	public static class AnagramMapper extends Mapper<Object, Text, Text, Text> {

		private Text word = new Text();
		public AnagramMapper(){
			
		}
		// pour le mapping on met comme clé le mot avec les caractere trié
		// la valeur est le mot en lui mm
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			Text res = new Text();
			while (itr.hasMoreTokens()) {
				// Lit le mot, le range dans word et passe au suivant
				word.set(itr.nextToken());
				// Transforme le mot en une chaine
				String s = word.toString();
				// range la chaine dans un tableau
				char[] data = s.toCharArray();
				// Trie le tableau
				Arrays.sort(data);
				// Récupère la chaine triée
				String trie = new String(data);
				// range la chaine triée dans le mot res
				res.set(trie);
				//Mise à jour de la map (mot trié , mot)
				context.write(res, word);
			}
		}

	}


	public static class AnagramReducer extends Reducer<Text, Text, Text, Text> {
		private Text result = new Text();
		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			String txt = "";
			for (Text val : values)
				txt += val.toString() + " ";
			result.set(txt);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("PID " +ManagementFactory.getRuntimeMXBean().getName());
		System.out.println("Start ?");
		Scanner sc = new Scanner(System.in);
		if(sc.nextLine().equals("yes")){
			Configuration conf = new Configuration();
			Job job = Job.getInstance(conf, "Anagrame");
			job.setJarByClass(Anagrams.class);
			job.setMapperClass(AnagramMapper.class);
			job.setCombinerClass(AnagramReducer.class);
			job.setReducerClass(AnagramReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		}
	}
}
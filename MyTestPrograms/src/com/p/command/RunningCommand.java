package com.p.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class RunningCommand {

	public static void main(String[] args) {

		String baseDir = "C:\\Users\\premendra.kumar\\git\\";
		JsonObject json = new JsonObject();
		json = JsonUtils.add(json, "baseDir", baseDir);
		List<String> children = getGitRepoDirNames(baseDir);
		json = JsonUtils.add(json, "children", children);
		// json.
		JsonArray outputArray = new JsonArray();
		for (String child : children) {
			JsonObject childJson = new JsonObject();
			childJson = JsonUtils.add(childJson, "dir", child);
			try {
				String output = gitStatus(child);
				System.out.println("==================");
				System.out.println(output);
				System.out.println("==================");
				childJson = JsonUtils.add(childJson, "output", output);

			} catch (Exception e) {
				childJson = JsonUtils.add(childJson, "output", "Error : " + ((e.getMessage() != null) ? e.getMessage()
						: " Exception occured. Please check in logs for detail."));
				e.printStackTrace();
			}
			outputArray.add(childJson);			
		}
		
		json.add("outputs", outputArray);
		
		try (PrintStream ps=new PrintStream(new File("C:\\Users\\premendra.kumar\\Desktop\\DUMP\\GitStatus.json"));){
			
			ps.println(json.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	private static class JsonUtils {

		public static JsonObject add(JsonObject o, String key, String value) {
			o.addProperty(key, value);
			return o;
		}

		public static JsonObject add(JsonObject o, String key, Boolean value) {
			o.addProperty(key, value);
			return o;
		}

		public static JsonObject add(JsonObject o, String key, Number value) {
			o.addProperty(key, value);
			return o;
		}

		public static JsonObject add(JsonObject o, String key, Character value) {
			o.addProperty(key, value);
			return o;
		}

		public static JsonObject add(JsonObject o, String key, List<String> value) {

			JsonArray array2 = new JsonArray();
			for (String file : value) {
				JsonPrimitive element = new JsonPrimitive(file);
				array2.add(element);
			}
			o.add(key, array2);

			return o;
		}
	}

	private static List<String> getGitRepoDirNames(String baseDir) {
		List<String> children = new ArrayList<String>();

		File git = new File("C:\\Users\\premendra.kumar\\git");

		File[] childarr = git.listFiles(new FileFilter() {

			@Override
			public boolean accept(File arg0) {

				if (arg0 != null && arg0.isDirectory()) {
					return true;
				}

				return false;
			}
		});

		if (childarr != null && childarr.length > 0) {
			for (File f : childarr) {
				children.add(f.getAbsolutePath());
			}
		}

		return children;
	}

	private static String gitStatus(String dir) throws IOException, InterruptedException {
		String output = "failed";

		StringBuffer sb = new StringBuffer();

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd " + dir + " && git status");
		sb.append(builder.command() + "\n");
		// builder.
		builder.redirectErrorStream(true);
		Process process = builder.start();

		// process.getOutputStream();
		process.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			sb.append(line + "\n");
		}
		output = sb.toString();

		return output;
	}

	private static void execCmd() {
		Process process;
		try {
			process = Runtime.getRuntime().exec("cmd /c dir");

			process.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
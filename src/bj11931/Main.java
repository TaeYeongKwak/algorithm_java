package bj11931;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		List<Integer> nums = new ArrayList<>();
		for (int n = 0; n < N; n++){
			nums.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(nums, Collections.reverseOrder());
		for (int num : nums){
			bw.write(num + "\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}
}

/**
 *
 */
package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tachibana446
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// サイズ
		final int field_size = 5;
		// 次に進むまでの時間
		double wait_time = 3.0;
		// 現在の経過時間
		double elapse_time = 0.0;
		// 生存フラグ
		boolean alive_flag = true;
		// 入力,前回の入力
		String input_str ="",prev_input_str = "";
		// 時刻
		long now_ms = System.currentTimeMillis();

		// 蛇
		// 最初の座標[x,y]	TODO:タプルを導入したい
		int[] now_coordinate = new int[2];
		now_coordinate[0] = 2;
		now_coordinate[1] = 2;
		// 座標のリスト
		List<int[]> coordinates = new ArrayList<int[]>();
		coordinates.add(now_coordinate);
		// 長さ
		int snake_size = 1;

		// エサの場所
		int[] bait_coordinate = new int[2];
		bait_coordinate[0] = 0;
		bait_coordinate[1] = 0;

		// メインループ
		while(alive_flag){

			// 経過時刻のリセット
			elapse_time = 0.0;
			// 現在時刻の更新
			now_ms = System.currentTimeMillis();
			// 入力文字のリセット
			prev_input_str = input_str;
			input_str = "";
			

			Scanner scanner = new Scanner(System.in);

			// 入力待機
			while(elapse_time < wait_time){
				input_str = scanner.next();
				// 経過時間の更新
				elapse_time += (System.currentTimeMillis() - now_ms) / 1000.0;
			}

			// 入力に応じて移動
			if(input_str.length() >= 1){
				input_str = input_str.substring(0, 1);
			}else{
				input_str = "";
			}
			// 移動
			switch (input_str) {
			case "w":
				now_coordinate[1] -= 1;
				coordinates.add(now_coordinate);
				break;
			case "s":
				now_coordinate[1] += 1;
				coordinates.add(now_coordinate);
				break;
			case "a":
				now_coordinate[0] -= 1;
				coordinates.add(now_coordinate);
				break;
			case "d":
				now_coordinate[0] += 1;
				coordinates.add(now_coordinate);
			default:
				// TODO:入力がなければ前回の入力 prev_input_strの方向に進むが、長くなるので省略
				now_coordinate[0] += 1;
				coordinates.add(now_coordinate);
				break;
			}

			// 判定
			// 壁と接触したか
			if(now_coordinate[0] < 0 || now_coordinate[0] > field_size || now_coordinate[1] < 0 || now_coordinate[1] > field_size){
				alive_flag = false;
			}
			// 自身と接触したか
			// 一番最後は今移動した場所なので、最後から２番めから純にサイズ分だけ見ていく
			for(int i=1;i < snake_size; i++){
				if(coordinates.get(coordinates.size() -1 - i) == now_coordinate){
					alive_flag = false;
					break;
				}
			}
			// 餌を食ったか
			if(now_coordinate == bait_coordinate){
				// 蛇が伸びる
				snake_size += 1;
				// TODO:次のエサが出る。蛇に被らないようにランダムに出すのがめんどい

				// TODO:待ち時間を減少させる。難しくなるので今は実装しない

			}
			// 表示
			System.out.println(now_coordinate[0] + "," + now_coordinate[1] + "," + snake_size);
			
			scanner.close();
		}
	}

}

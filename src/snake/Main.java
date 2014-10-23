/**
 *
 */
package snake;

import java.util.Scanner;

/**
 * @author Tachibana446
 *
 */
public class Main{

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
		Snake snake = new Snake(2, 2);

		// エサの場所
		int[] bait_coordinate = new int[2];
		bait_coordinate[0] = 0;
		bait_coordinate[1] = 0;

		// スキャナ
		Scanner scanner = new Scanner(System.in);


		// メインループ
		while(alive_flag){

			// 経過時刻のリセット
			elapse_time = 0.0;
			// 現在時刻の更新
			now_ms = System.currentTimeMillis();
			// 入力文字のリセット
			prev_input_str = input_str;
			input_str = "";


			// 入力待機
			while(input_str.length() <= 0){
				input_str = scanner.next();
				// 経過時間の更新
				elapse_time += (System.currentTimeMillis() - now_ms) / 1000.0;
			}

			// 入力に応じて移動
			input_str = snake.move(input_str);
			// 入力がなければ前回の入力 prev_input_strの方向に進む
			if(input_str == ""){
				input_str = snake.move(prev_input_str);
			}

			// 判定
			//壁とぶつかったか
			if(snake.isCollidingWall(field_size)){
				alive_flag = false;
			}
			// 自身と接触したか
			if(snake.isCollidingOneself()){
				alive_flag = false;
			}
			// 餌を食ったか
			if(snake.isCollidingBait(bait_coordinate)){
				// 蛇が伸びる
				snake.grawSize();
				// TODO:次のエサが出る。蛇に被らないようにランダムに出すのがめんどい

				// TODO:待ち時間を減少させる。難しくなるので今は実装しない

			}
			// 表示
			System.out.println(snake.toString());
		}

		System.out.println("GAME OVER!");
		scanner.close();
	}
}


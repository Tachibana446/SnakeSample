/**
 *
 */
package snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nameka
 *
 */
public class Snake {
	// 最初の座標 TODO:タプルを導入したい
	int[] now_coordinate = new int[2];
	// 座標のリスト
	List<int[]> coordinates = new ArrayList<int[]>();
	// 長さ
	int size;


	public Snake(int x,int y) {
		super();
		now_coordinate[0] = x;
		now_coordinate[1] = y;
		this.size = 1;
	}


	public List<int[]> getCoordinates() {
		return coordinates;
	}


	public void setCoordinates(List<int[]> coordinates) {
		this.coordinates = coordinates;
	}

	public void addCoordinates(int x,int y){
		int[] coordinate = new int [2];
		coordinate[0] = x;
		coordinate[1] = y;

		coordinates.add(coordinate);
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	/**
	 * sizeを１大きくする
	 */
	public void grawSize(){
		size += 1;
	}

	public int getX() {
		return now_coordinate[0];
	}


	public int getY() {
		return now_coordinate[1];
	}

	public int[] getNowCorrdinate(){
		return now_coordinate;
	}


	/**
	 * 入力された文字に応じて移動
	 * @param input_str WASDで移動
	 * @return
	 */
	public String move(String input_str) {
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
			break;
		default:
			break;
		}
		return input_str;
	}

	/**
	 * 壁と衝突したか判定する
	 * 現在の座標がステージの大きさを超えていたらtrueを返します
	 * @param field_size ステージの大きさ
	 * @return
	 */
	public boolean isCollidingWall(final int field_size) {
		// 壁と接触したか
		if(now_coordinate[0] <= 0 || now_coordinate[0] >= field_size || now_coordinate[1] <= 0 || now_coordinate[1] >= field_size){
			return true;
		}
		return false;
	}

	/**
	 * 自分自身と衝突しているか
	 * @return
	 */
	public boolean isCollidingOneself() {
		// 一番最後は今移動した場所なので、最後から２番めから純にサイズ分だけ見ていく
		for(int i=1;i < size; i++){
			if(coordinates.get(coordinates.size() -1 - i) == now_coordinate){
				return true;
			}
		}
		return false;
	}

	/**
	 * エサを食べたか
	 * @param bait_coordinate エサの座標[x,y]
	 * @return
	 */
	public boolean isCollidingBait(int[] bait_coordinate) {
		if(now_coordinate == bait_coordinate){
			return true;
		}
		return false;
	}


	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Snake [now_coordinate=" + Arrays.toString(now_coordinate)
				 + ", size=" + size + "]";
	}
}

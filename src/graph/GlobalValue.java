package graph;

public interface GlobalValue {
	
	int[][] colorPalettes = {
		{0xf39800,0xf2a218,0xf2ab30,0xf2b449,0xf2bd61},//橙
		{0xe6002d,0xe6002e,0xe62e53,0xe64565,0xe65c77},//赤
		{0x00b350,0x009944,0x009948,0x1f9956,0x2e995e},//緑
		{0x36318f,0x07008f,0x150e8f,0x302b8f,0x3d398f},//青
		{0x801e6c,0x74085e,0x800d69,0x803370,0x804073},//紫
		{0x00e6da,0x00ccc2,0x009e96,0x109e97,0x3f9e99},//水
		{0x5d310c,0x5c2900,0x5c3312,0x5c381c,0x5c3d25},//茶
		{0xff6666,0xf07070,0xf29696,0xf08b8b,0xf59494},//ピンク
		{0x88e02e,0x92e043,0x96db50,0xb2d63c,0xccf058},//ライム
		{0x07194b,0x0f2257,0x12296a,0x17213e,0x0f296f},//藍
		{0x339933,0x38ac38,0x4fac4f,0x469546,0x42b742},//緑
		{0x3978f5,0x417cf4,0x6699ff,0x5d8ae2,0x2c65d6}//青*/
	};
	
	/**スプリングアルゴリズムのパラメータ  */
	//TODO: バネ定数(BOUNCE < 0.1[推奨])
	double BOUNCE = 0.05;
	
	//TODO: 減衰定数(ATTENUATION < 1[必須])
	double ATTENUATION = 0.8;
	
	//TODO: クーロン数
	double COULOMB = 40;

}

package lab3;

public class Pojo {

//	Источник: Il = 10; Ia = 1, L = -2i - 2j + 2k.
//	Наблюдатель: S = 2i + j + 2k.
//	Поверхность: разбить грань на 4 треугольника,
//	d = 0; K = 1; поверхность блестящая металическая,
//	ks = 0,8; ka = kd = 0,15; n = 5.
	
	
	private float
		il, // 
		ia,
		li,
		lj,
		lk,
		si,
		sj,
		sk,
		d,
		k,
		ks,
		ka,
		kd,
		n;

	public float getLi() {
		return li;
	}

	public void setLi(float li) {
		this.li = li;
	}

	public float getLj() {
		return lj;
	}

	public void setLj(float lj) {
		this.lj = lj;
	}

	public float getLk() {
		return lk;
	}

	public void setLk(float lk) {
		this.lk = lk;
	}

	public float getIl() {
		return il;
	}

	public void setIl(float il) {
		this.il = il;
	}

	public float getIa() {
		return ia;
	}

	public void setIa(float ia) {
		this.ia = ia;
	}

	public float getSi() {
		return si;
	}

	public void setSi(float si) {
		this.si = si;
	}

	public float getSj() {
		return sj;
	}

	public void setSj(float sj) {
		this.sj = sj;
	}

	public float getSk() {
		return sk;
	}

	public void setSk(float sk) {
		this.sk = sk;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public float getK() {
		return k;
	}

	public void setK(float k) {
		this.k = k;
	}

	public float getKs() {
		return ks;
	}

	public void setKs(float ks) {
		this.ks = ks;
	}

	public float getKa() {
		return ka;
	}

	public void setKa(float ka) {
		this.ka = ka;
	}

	public float getKd() {
		return kd;
	}

	public void setKd(float kd) {
		this.kd = kd;
	}

	public float getN() {
		return n;
	}

	public void setN(float n) {
		this.n = n;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ il : " + il + ", ");
		sb.append("ia : " + ia + ", ");
		sb.append("sa : " + ia + ", ");
		sb.append("li : " + li + ", ");
		sb.append("lj : " + lj + ", ");
		sb.append("lk : " + lk + ", ");
		sb.append("si : " + si + ", ");
		sb.append("sj : " + sj + ", ");
		sb.append("sk : " + sk + ", ");
		sb.append("d : " + d + ", ");
		sb.append("k : " + k + ", ");
		sb.append("ks : " + ks + ", ");
		sb.append("ka : " + ka + ", ");
		sb.append("kd : " + kd + ", ");
		sb.append("n : " + n + " }");
		return sb.toString();
	}
	
}

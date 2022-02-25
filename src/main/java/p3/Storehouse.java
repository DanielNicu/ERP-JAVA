package p3;
/**
 * a class that cepps count of the materials of the company
 * @author UserW10
 *
 */
public class Storehouse {
	int ink;
	int paper;
	int glue;
	int cover;
	int max_ink;
	int max_paper;
	int max_glue;
	int max_cover;
	/**
	 * 
	 * @param ink material
	 * @param paper material
	 * @param glue material
	 * @param cover material
	 * @param max_ink maximum ink capacity
	 * @param max_paper maximum paper capacity
	 * @param max_glue maximum glue capacity
	 * @param max_cover maximum cover capacity
	 */
	public Storehouse(int ink, int paper, int glue, int cover, int max_ink, int max_paper, int max_glue,
			int max_cover) {
		super();
		this.ink = ink;
		this.paper = paper;
		this.glue = glue;
		this.cover = cover;
		this.max_ink = max_ink;
		this.max_paper = max_paper;
		this.max_glue = max_glue;
		this.max_cover = max_cover;
	}
	public int getInk() {
		return ink;
	}
	public void setInk(int ink) {
		this.ink = ink;
	}
	public int getPaper() {
		return paper;
	}
	public void setPaper(int paper) {
		this.paper = paper;
	}
	public int getGlue() {
		return glue;
	}
	public void setGlue(int glue) {
		this.glue = glue;
	}
	public int getCover() {
		return cover;
	}
	public void setCover(int cover) {
		this.cover = cover;
	}
	public int getMax_ink() {
		return max_ink;
	}
	public void setMax_ink(int max_ink) {
		this.max_ink = max_ink;
	}
	public int getMax_paper() {
		return max_paper;
	}
	public void setMax_paper(int max_paper) {
		this.max_paper = max_paper;
	}
	public int getMax_glue() {
		return max_glue;
	}
	public void setMax_glue(int max_glue) {
		this.max_glue = max_glue;
	}
	public int getMax_cover() {
		return max_cover;
	}
	public void setMax_cover(int max_cover) {
		this.max_cover = max_cover;
	}
	/**
	 * verifies the stocks
	 * @return boolean
	 */
	public boolean verifyGlue()
	{
		if(getGlue()<=getMax_glue()*10/100)
			return true;
		return false;
	}
	/**
	 * verifies the stocks
	 * @return boolean
	 */
	public boolean verifyPaper()
	{
		if(getPaper()<=getMax_paper()*10/100)
			return true;
		return false;
	}
	/**
	 * verifies the stocks
	 * @return boolean
	 */
	public boolean verifyInk()
	{
		if(getInk()<=getMax_ink()*10/100) 
			return true;
		return false;
	}
	/**
	 * verifies the stocks
	 * @return boolean
	 */
	public boolean verifyCover()
	{
		if(getCover()<=getMax_cover()*10/100)
			return true;
		return false;
	}
	/**
	 * modifies the values
	 * @param i ink
	 * @param p paper
	 * @param g glue
	 * @param c cover
	 */
	public void addDelivery(int i, int p, int g, int c)
	{
		ink=ink+i;
		paper=paper+p;
		glue=glue+g;
		cover=cover+c;
	}
}

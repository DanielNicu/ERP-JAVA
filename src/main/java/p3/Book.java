package p3;
/**
 * 
 * The book types and the required materials
 *
 */
public class Book {
	int type;
	int ink;
	int paper;
	int glue;
	int cover;
	/**
	 * 
	 * @param type the id of the book type
	 * @param ink the ink required to craft the book
	 * @param paper the paper required to craft the book
	 * @param glue the glue required to craft the book
	 * @param cover the cover required to craft the book
	 */
	public Book(int type, int ink, int paper, int glue, int cover) {
		super();
		this.type = type;
		this.ink = ink;
		this.paper = paper;
		this.glue = glue;
		this.cover = cover;
	}
	/**
	 * 
	 * @return book type
	 */
	public int getType() {
		return type;
	}
	/**
	 * 
	 * @param book type
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * 
	 * @return ink needed
	 */
	public int getInk() {
		return ink;
	}
	/**
	 * 
	 * @param ink ink needed
	 */
	public void setInk(int ink) {
		this.ink = ink;
	}
	/**
	 * 
	 * @return paper needed
	 */
	public int getPaper() {
		return paper;
	}
	/**
	 * 
	 * @param paper paper needed
	 */
	public void setPaper(int paper) {
		this.paper = paper;
	}
	/**
	 * 
	 * @return glue needed
	 */
	public int getGlue() {
		return glue;
	}
	/**
	 * 
	 * @param glue glue needed
	 */
	public void setGlue(int glue) {
		this.glue = glue;
	}
	/**
	 * 
	 * @return cover needed
	 */
	public int getCover() {
		return cover;
	}
	/**
	 * 
	 * @param cover cover needed
	 */
	public void setCover(int cover) {
		this.cover = cover;
	}
	
	
	
}

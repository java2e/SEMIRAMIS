
package pelops.users;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public class User {
	// Attributes of User
	private int usrId; // private int kullaniciId;
	private String usrName; // private String kullaniciAdi;
	private String usrPwd; // private String kullaniciSifre;
	private String usrAdSoyad;// private String kullaniciAdSoyad;
	private String usrMail; // private String kullaniciEmail;
	private String usrTel; // private String kullaniciTelefon;
	private String usrAck; // private String kullaniciAciklama;
	private int usrKullaniciTipi; // private int kullaniciRolId;
	private String usrRolAck;// private String kullaniciRolAck;
	private String usrPhotoUrl;

	private boolean usrIptal;// private boolean kullaniciIptal;
	private int guncelleyenKullaniciId; // private int guncelleyenKullaniciId;
	private String guncelleyenKullaniciAck;
	private Date guncellemeZamani; // private Date guncellemeZamani;

	private List<Integer> page;
	private List<Integer> writeList;
	private List<Integer> readList;

	// constructor
	public User() {
	}

	// constructor
	public User(int usrId, String usrName, String usrPwd, String usrAdSoyad, String usrMail, String usrTel,
			String usrAck, int usrKullaniciTipi, String usrRolAck, boolean usrIptal, int guncelleyenKullaniciId,
			String guncelleyenKullaniciAck, Date guncellemeZamani, List<Integer> page, List<Integer> writeList,
			List<Integer> readList) {
		super();
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrPwd = usrPwd;
		this.usrAdSoyad = usrAdSoyad;
		this.usrMail = usrMail;
		this.usrTel = usrTel;
		this.usrAck = usrAck;
		this.usrKullaniciTipi = usrKullaniciTipi;
		this.usrRolAck = usrRolAck;
		this.usrIptal = usrIptal;
		this.guncelleyenKullaniciId = guncelleyenKullaniciId;
		this.guncelleyenKullaniciAck = guncelleyenKullaniciAck;
		this.guncellemeZamani = guncellemeZamani;
		this.page = page;
		this.writeList = writeList;
		this.readList = readList;
	}

	// getters and setters of attributes
	public int getUsrId() {
		return usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPwd() {
		return usrPwd;
	}

	public void setUsrPwd(String usrPwd) {
		this.usrPwd = usrPwd;
	}

	public String getUsrAdSoyad() {
		return usrAdSoyad;
	}

	public void setUsrAdSoyad(String usrAdSoyad) {
		this.usrAdSoyad = usrAdSoyad;
	}

	public String getUsrMail() {
		return usrMail;
	}

	public void setUsrMail(String usrMail) {
		this.usrMail = usrMail;
	}

	public String getUsrTel() {
		return usrTel;
	}

	public void setUsrTel(String usrTel) {
		this.usrTel = usrTel;
	}

	public String getUsrAck() {
		return usrAck;
	}

	public void setUsrAck(String usrAck) {
		this.usrAck = usrAck;
	}

	public int getUsrKullaniciTipi() {
		return usrKullaniciTipi;
	}

	public void setUsrKullaniciTipi(int usrKullaniciTipi) {
		this.usrKullaniciTipi = usrKullaniciTipi;
	}

	public String getUsrRolAck() {
		return usrRolAck;
	}

	public void setUsrRolAck(String usrRolAck) {
		this.usrRolAck = usrRolAck;
	}

	public boolean isUsrIptal() {
		return usrIptal;
	}

	public void setUsrIptal(boolean usrIptal) {
		this.usrIptal = usrIptal;
	}

	public int getGuncelleyenKullaniciId() {
		return guncelleyenKullaniciId;
	}

	public void setGuncelleyenKullaniciId(int guncelleyenKullaniciId) {
		this.guncelleyenKullaniciId = guncelleyenKullaniciId;
	}

	public String getGuncelleyenKullaniciAck() {
		return guncelleyenKullaniciAck;
	}

	public void setGuncelleyenKullaniciAck(String guncelleyenKullaniciAck) {
		this.guncelleyenKullaniciAck = guncelleyenKullaniciAck;
	}

	public Date getGuncellemeZamani() {
		return guncellemeZamani;
	}

	public void setGuncellemeZamani(Date guncellemeZamani) {
		this.guncellemeZamani = guncellemeZamani;
	}

	public List<Integer> getPage() {
		return page;
	}

	public void setPage(List<Integer> page) {
		this.page = page;
	}

	public List<Integer> getWriteList() {
		return writeList;
	}

	public void setWriteList(List<Integer> writeList) {
		this.writeList = writeList;
	}

	public List<Integer> getReadList() {
		return readList;
	}

	public void setReadList(List<Integer> readList) {
		this.readList = readList;
	}

	public String getUsrPhotoUrl() {
		return usrPhotoUrl;
	}

	public void setUsrPhotoUrl(String usrPhotoUrl) {
		this.usrPhotoUrl = usrPhotoUrl;
	}

	/**
	 * 
	 */
	public User clone() {
		User clone = new User();

		clone.setGuncellemeZamani(this.getGuncellemeZamani());
		clone.setGuncelleyenKullaniciAck(this.getGuncelleyenKullaniciAck());
		clone.setGuncelleyenKullaniciId(this.getGuncelleyenKullaniciId());
		clone.setPage(this.getPage());
		clone.setReadList(this.getReadList());
		clone.setUsrAck(this.getUsrAck());
		clone.setUsrAdSoyad(this.getUsrAdSoyad());
		clone.setUsrId(this.getUsrId());
		clone.setUsrIptal(this.isUsrIptal());
		clone.setUsrKullaniciTipi(this.getUsrKullaniciTipi());
		clone.setUsrMail(this.getUsrMail());
		clone.setUsrName(this.getUsrName());
		clone.setUsrPwd(this.getUsrPwd());
		clone.setUsrRolAck(this.getUsrRolAck());
		clone.setUsrTel(this.getUsrTel());
		clone.setWriteList(this.getWriteList());

		return clone;
	}

}
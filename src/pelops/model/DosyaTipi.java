package pelops.model;

public class DosyaTipi {

	int id, takip_tipi_id, takip_yolu_id, borc_tipi_id, borca_esas_evrak_id,talep_edilen_hakkinda_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTakip_tipi_id() {
		return takip_tipi_id;
	}
	public void setTakip_tipi_id(int takip_tipi_id) {
		this.takip_tipi_id = takip_tipi_id;
	}
	public int getTakip_yolu_id() {
		return takip_yolu_id;
	}
	public void setTakip_yolu_id(int takip_yolu_id) {
		this.takip_yolu_id = takip_yolu_id;
	}
	public int getBorc_tipi_id() {
		return borc_tipi_id;
	}
	public void setBorc_tipi_id(int borc_tipi_id) {
		this.borc_tipi_id = borc_tipi_id;
	}
	public int getBorca_esas_evrak_id() {
		return borca_esas_evrak_id;
	}
	public void setBorca_esas_evrak_id(int borca_esas_evrak_id) {
		this.borca_esas_evrak_id = borca_esas_evrak_id;
	}
	public int getTalep_edilen_hakkinda_id() {
		return talep_edilen_hakkinda_id;
	}
	public void setTalep_edilen_hakkinda_id(int talep_edilen_hakkinda_id) {
		this.talep_edilen_hakkinda_id = talep_edilen_hakkinda_id;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	String adi;
}

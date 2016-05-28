package pelops.chronology.controller;

import java.util.ArrayList;
import java.util.List;

import pelops.chronology.model.Instance;
import pelops.chronology.model.ReportChronology;
import pelops.controller.AktifBean;
import pelops.report.model.ConstructedData;

public class ChronologyUtil {
	private static ChronologyUtil util;

	private static ChronologyUtil createInstance() {
		util = new ChronologyUtil();
		return util;
	}

	public static ChronologyUtil getInstance() {
		if (util == null) {
			util = createInstance();
		}
		return util;
	}

	public void insertInstance(Object object) throws Exception {
		if (object instanceof ReportChronology) {
			ReportChronologyUtil.getInstance().insertObjToDB(object);
		} else if (object instanceof Instance) {
			String icraDosyaNo = "";
			if (((Instance) object).getIcraDosyaNo() != null) {
				icraDosyaNo = ((Instance) object).getIcraDosyaNo();
			}
			InstanceUtil.getInstance().insertInstance(icraDosyaNo, ((Instance) object).getIcraDosyaID(),
					((Instance) object).getOlayAdi(), ((Instance) object).getAciklama(),
					((Instance) object).getState());
		}
	}

	public void insertInstances(List instances, Integer state) throws Exception {
		boolean raporCikti = false;
		boolean olay = false;
		if (instances.size() > 0) {
			if (instances.get(0) instanceof ReportChronology) {
				raporCikti = true;
			} else if (instances.get(0) instanceof Instance) {
				olay = true;
			}
		}
		if (raporCikti) {
			ArrayList<ConstructedData> list2 = (ArrayList<ConstructedData>) instances;
			ReportChronologyUtil.getInstance().insertAll(list2);
		} else if (olay) {

			if (state != null) {
				InstanceUtil.getInstance().insertInstances(instances, state);
			}

		}

	}

}

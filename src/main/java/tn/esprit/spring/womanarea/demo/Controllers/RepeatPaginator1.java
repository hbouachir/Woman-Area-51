package tn.esprit.spring.womanarea.demo.Controllers;

import java.util.List;

public class RepeatPaginator1 {

	private static final int DEFAULT_RECORDS_NUMBER = 2;
	private static final int DEFAULT_PAGE_INDEX = 1;

	private int records;
	private int recordsTotal;
	private int pageIndex;
	private int pages;
	private List<?> origModel;
	private List<?> model;

	public RepeatPaginator1(List<?> model) {
		this.origModel = model;
		this.records =6 ;
		this.pageIndex = DEFAULT_PAGE_INDEX;
		this.recordsTotal = model.size();

		if (records > 0) {
			pages = records <= 0 ? 1 : recordsTotal / records;

			if (recordsTotal % records > 0) {
				pages++;
			}

			if (pages == 0) {
				pages = 1;
			}
		} else {
			records = 1;
			pages = 1;
		}

		updateModel();
	}

	public void updateModel() {
		int fromIndex = getFirst();
		int toIndex = getFirst() + records;

		if (toIndex > this.recordsTotal) {
			toIndex = this.recordsTotal;
		}

		this.model = origModel.subList(fromIndex, toIndex);
	}

	public void next() {
		if (this.pageIndex < pages) {
			this.pageIndex++;
		}

		updateModel();
	}

	public void page(int page) {
		pageIndex=page;
		updateModel();
		
	}

	public void prev() {
		if (this.pageIndex > 1) {
			this.pageIndex--;
		}

		updateModel();
	}

	public int getRecords() {
		return records;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getPages() {
		return pages;
	}

	public int getFirst() {
		return (pageIndex * records) - records;
	}

	public List<?> getModel() {
		return model;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}

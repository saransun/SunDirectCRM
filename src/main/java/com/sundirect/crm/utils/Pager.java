package com.sundirect.crm.utils;

import org.springframework.data.domain.Page;

public class Pager<T> {

	private int buttonsToShow = 5;

	private int startPage;

	private int endPage;

	private int totalPages;

	private int number;

	private int selectedPageSize = 50;

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSelectedPageSize() {
		return selectedPageSize;
	}

	public void setSelectedPageSize(int selectedPageSize) {
		this.selectedPageSize = selectedPageSize;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	private Page<T> page;

	private String url;

	private Long totalElements;

	private int numberOfElements;

	public Pager(int buttonsToShow, Page<T> page, String url) {

		this.page = page;

		this.totalElements = page.getTotalElements();

		this.numberOfElements = page.getNumberOfElements();

		this.url = url;

		this.totalPages = page.getTotalPages();
		this.number = page.getNumber();

		int currentPage = page.getNumber();

		setButtonsToShow(buttonsToShow);

		int halfPagesToShow = getButtonsToShow() / 2;

		if (totalPages <= getButtonsToShow()) {
			setStartPage(1);
			setEndPage(totalPages);

		} else if (currentPage - halfPagesToShow <= 0) {
			setStartPage(1);
			setEndPage(getButtonsToShow());

		} else if (currentPage + halfPagesToShow == totalPages) {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(totalPages);

		} else if (currentPage + halfPagesToShow > totalPages) {
			setStartPage(totalPages - getButtonsToShow() + 1);
			setEndPage(totalPages);

		} else {
			setStartPage(currentPage - halfPagesToShow);
			setEndPage(currentPage + halfPagesToShow);
		}

	}

	public int getButtonsToShow() {
		return buttonsToShow;
	}

	public void setButtonsToShow(int buttonsToShow) {
		if (buttonsToShow % 2 != 0) {
			this.buttonsToShow = buttonsToShow;
		} else {
			throw new IllegalArgumentException("Must be an odd value!");
		}
	}

}

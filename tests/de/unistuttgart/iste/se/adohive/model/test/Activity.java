package de.unistuttgart.iste.se.adohive.model.test;

import java.sql.Date;

import de.unistuttgart.iste.se.adohive.model.IActivity;

public final class Activity implements IActivity {
		private Integer assistantId = IndependentTestDataProvider.randomInt();
		private String content = IndependentTestDataProvider.randomString();
		private Integer courseId = IndependentTestDataProvider.randomInt();
		private Date date = IndependentTestDataProvider.randomDate();
		private String documentType = IndependentTestDataProvider.randomString();
		private String processor = IndependentTestDataProvider.randomFixedLengthDigitString(2);
		private String remark = IndependentTestDataProvider.randomString();
		private String sender = IndependentTestDataProvider.randomString();
		private String type = IndependentTestDataProvider.randomString();
		private Integer id;

		@Override
		public Integer getAssistantId() {
			return assistantId;
		}

		@Override
		public String getContent() {
			return content;
		}

		@Override
		public Integer getCourseId() {
			return courseId;
		}

		@Override
		public Date getDate() {
			return date;
		}

		@Override
		public String getDocumentType() {
			return documentType;
		}

		@Override
		public String getProcessor() {
			return processor;
		}

		@Override
		public String getRemark() {
			return remark;
		}

		@Override
		public String getSender() {
			return sender;
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public void setAssistantId(Integer assistantId) {
			this.assistantId = assistantId;
			
		}

		@Override
		public void setContent(String content) {
			this.content = content;
			
		}

		@Override
		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
			
		}

		@Override
		public void setDate(Date date) {
			this.date = date;
			
		}

		@Override
		public void setDocumentType(String documentType) {
			this.documentType = documentType;
			
		}

		@Override
		public void setProcessor(String processor) {
			this.processor = processor;
			
		}

		@Override
		public void setRemark(String remark) {
			this.remark = remark;
			
		}

		@Override
		public void setSender(String sender) {
			this.sender = sender;
			
		}

		@Override
		public void setType(String type) {
			this.type = type;
			
		}

		@Override
		public Integer getId() {
			return id;
		}

		@Override
		public void setId(Integer id) {
			this.id = id;
			
		}

		public IActivity clone() {
			IActivity a = new Activity();
			a.setAssistantId(this.assistantId);
			a.setContent(this.content);
			a.setCourseId(this.courseId);
			a.setDate(this.date);
			a.setDocumentType(this.documentType);
			a.setId(this.id);
			a.setProcessor(this.processor);
			a.setRemark(this.remark);
			a.setSender(this.sender);
			a.setType(this.type);
			return a;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof IActivity) {
				IActivity a = (IActivity) o;
				return  a.getId().equals(this.id) &&
						a.getAssistantId().equals(this.assistantId) &&
						StringHelper.equals(a.getContent(), this.content) &&
						a.getCourseId().equals(this.courseId) &&
						a.getDate().equals(this.date) &&
						StringHelper.equals(a.getDocumentType(), this.documentType) &&
						StringHelper.equals(a.getProcessor(), this.processor) &&
						StringHelper.equals(a.getRemark(), this.remark) &&
						StringHelper.equals(a.getSender(), this.sender) &&
						StringHelper.equals(a.getType(), this.type);
			} else {
				return false;
			}
		}
	}
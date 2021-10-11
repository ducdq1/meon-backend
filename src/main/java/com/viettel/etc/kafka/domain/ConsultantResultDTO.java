package com.viettel.etc.kafka.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Comment
 **/
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultantResultDTO implements Serializable {
	private Integer actionBy;

	private Integer patientId;
	@NotNull
	private Integer bookingId;

	private String concludesDisease;

	private String treatmentDirection;

	@NotNull
	private Integer typeOfExamination;

	private Date reExaminationDate;
	@NotNull
	private Date examinationDate;

	private Date finishExaminationDate;
	@Valid
	private List<ConsultantResultDTO.Attacments> attachments;
	@Valid
	private List<ConsultantResultDTO.Drug> drugs;
	private String reasonsMedicalexamination;
	private String symptoms;
	private String rightEyeWithoutGlasses;
	private String leftEyeWithoutGlasses;
	private String rightEyeWithGlasses;
	private String leftEyeWithGlasses;
	private String bodySkin;
	private String bodySkinOther;
	private String bodyPartHeart;
	private String bodyPartRespiratory;
	private String bodyPartDigest;
	private String bodyPartUrinary;
	private String bodyPartOsteoarthritis;
	private String bodyPartEndocrine;
	private String bodyPartNerve;
	private String bodyPartMental;
	private String bodyPartSurgical;
	private String bodyPartGynecology;
	private String bodyPartEarNoseThroat;
	private String bodyPartDentomaxillofacial;
	private String bodyPartEye;
	private String bodyPartSkin;
	private String bodyPartNutrition;
	private String bodyPartMotive;
	private String bodyPartEvaluation;
	private String bodyPartOther;
	@Valid
	private List<ConsultantResultDTO.Subclinical> subclinical;
	@Valid
	private ConsultantResultDTO.Diagnostic diagnostic;
	@Valid
	private ConsultantResultDTO.LivingFunction livingFunction;

	@Data
	public static class Subclinical {
		private String serviceName;
		@JsonProperty("code")
		private String serviceCode;
		private String concludes;
		@NotNull
		private Integer groupId;
		private Date decisionDate;
		private List<ServiceIndex> indexs;
		private List<Attacments> attachments;
		private String results;
		private String suggestions;
		private Integer serviceId;
	}

	@Data
	public static class ServiceIndex {
		private String indexName;
		private Integer orderNumber;
		private String value;
	}

	@Data
	public static class Diseases {
		private String diseasesCode;
		private String diseasesName;
	}

	@Data
	public static class Diagnostic {
		private List<ConsultantResultDTO.Diseases> diseases;
	}

	@Data
	public static class Attacments {

		@NotNull
		private String fileName;

		/* file base 64 */
		@NotNull
		private String file;

		/* filr patch de luu vao db */
//		@JsonProperty(access = JsonProperty.Access.READ_ONLY)
		private String url;
	}

	@Data
	public static class Drug {
		private  Integer drugId;
		@NotNull
		private String drugName;
		@NotNull
		private String unit;
		@NotNull
		@Length(max = 5)
		private String methodCode;
		@NotNull
		private Integer quantity;
		@NotNull
		private Integer morningAmount;
		@NotNull
		private Integer noonAmount;
		@NotNull
		private Integer afternoonAmount;
		@NotNull
		private Integer eveningAmount;
		@NotNull
		private String dosageDescription;
		@NotNull
		private String notes;
	}

	@Data
	public static class LivingFunction {
		Integer breath;
		Integer pulse;
		Integer bloodPressureMax;
		Integer bloodPressureMin;
		Integer waistCircumference;
		Double temperature;
		Double height;
		Double weight;
		Double bmi;

		public Double getBmi() {
			if (this.weight != null && this.height != null &&
					this.weight > 0 && this.height > 0) {
				double heightm = (double) this.height / 100;

				return this.weight / Math.pow(heightm, 2);
			}
			return null;
		}
	}
}

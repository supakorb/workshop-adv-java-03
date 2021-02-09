package badcode;

public class RegisterBusiness {

	public Integer register(SpeakerRepository repository, Speaker speaker) {
		validateRegisterData(speaker);
		return saveSpeaker(repository, speaker);
	}
	
	private void validateRegisterData(Speaker speaker) {
		String[] domains = { "gmail.com", "live.com" };
		speaker.checkEmptyFirstName();
		speaker.checkEmptyLastName();
		speaker.checkValidEmail(domains);
		speaker.setRegistrationFee(getFee(speaker.getExp()));
	}
	
	private Integer saveSpeaker(SpeakerRepository repository, Speaker speaker) {
		Integer speakerId;
		try {
			speakerId = repository.saveSpeaker(speaker);
		} catch (Exception exception) {
			throw new SaveSpeakerException("Can't save a speaker.");
		}
		return speakerId;
	}
	
	public int getFee(int exp) {
		int fee = 0;
		if (exp <= 1) {
			fee = 500;
		} else if (exp <= 3) {
			fee = 250;
		} else if (exp <= 5) {
			fee = 100;
		} else if (exp <= 9) {
			fee = 50;
		}
		return fee;
	}
}

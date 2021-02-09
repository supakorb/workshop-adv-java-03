package badcode;

public class RegisterBusiness {

	public Integer register(SpeakerRepository repository, Speaker speaker) {
		Integer speakerId;
		String[] domains = { "gmail.com", "live.com" };
		
		speaker.checkEmptyFirstName();
		speaker.checkEmptyLastName();
		speaker.checkValidEmail(domains);
		
		int exp = speaker.getExp();
		// TODO :: create test case to cover this condition
		speaker.setRegistrationFee(getFee(exp));
		try {
			speakerId = repository.saveSpeaker(speaker);
		} catch (Exception exception) {
			throw new SaveSpeakerException("Can't save a speaker.");
		}
		return speakerId;
	}
	
	private int getFee(int exp) {
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

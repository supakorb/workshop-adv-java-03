package badcode;

import java.util.Arrays;

public class RegisterBusiness {

	public Integer register(SpeakerRepository repository, Speaker speaker) {
		Integer speakerId;
		String[] domains = { "gmail.com", "live.com" };
		
		if (speaker.getFirstName() == null || speaker.getFirstName().trim().equals("")) {
			throw new ArgumentNullException("First name is required.");
		}
		if (speaker.getLastName() == null || speaker.getLastName().trim().equals("")) {
			throw new ArgumentNullException("Last name is required.");
		}
		if (speaker.getEmail() == null || speaker.getEmail().trim().equals("")) {
			throw new ArgumentNullException("Email is required.");
		}
		int indexDomain = speaker.getEmail().indexOf("@");
		boolean isValidDomain = indexDomain >= 0 && indexDomain != speaker.getEmail().length() - 1;
		String emailDomain =  isValidDomain ? speaker.getEmail().split("@")[1] : "";
		if (Arrays.stream(domains).filter(it -> it.equals(emailDomain)).count() == 0) {
			throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our standard rules.");
		}
		
		int exp = speaker.getExp();
		// TODO :: create test case to cover this condition
		if (exp <= 1) {
			speaker.setRegistrationFee(500);
		} else if (exp >= 2 && exp <= 3) {
			speaker.setRegistrationFee(250);
		} else if (exp >= 4 && exp <= 5) {
			speaker.setRegistrationFee(100);
		} else if (exp >= 6 && exp <= 9) {
			speaker.setRegistrationFee(50);
		} else {
			speaker.setRegistrationFee(0);
		}
		try {
			speakerId = repository.saveSpeaker(speaker);
		} catch (Exception exception) {
			throw new SaveSpeakerException("Can't save a speaker.");
		}
		return speakerId;
	}
}

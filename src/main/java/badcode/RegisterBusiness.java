package badcode;

public class RegisterBusiness {

	public Integer register(SpeakerRepository repository, Speaker speaker) {
		validateRegisterData(speaker);
		speaker.setRegistrationFee(getFee(speaker.getExp()));
		return repository.saveSpeaker(speaker);
	}
	
	private void validateRegisterData(Speaker speaker) {
		String[] domains = { "gmail.com", "live.com" };
		speaker.checkEmptyFirstName();
		speaker.checkEmptyLastName();
		speaker.checkValidEmail(domains);
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

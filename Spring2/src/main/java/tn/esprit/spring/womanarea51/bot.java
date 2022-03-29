package tn.esprit.spring.womanarea51;
import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.womanarea51.services.IExpertInterviewService;

public class bot {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	private static String reponse;
	@Autowired
	static
	IExpertInterviewService exint;
	
	

public static String chat(String textLine){
		try {
			
			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("super", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			

			
				System.out.print("Human : "+textLine+"\n");
				//System.out.print(textLine);
				//textLine = IOUtils.readInputTextLine();
				/*if ((textLine == null) || (textLine.length() < 1))
					textLine = MagicStrings.null_input;*/
				
				if (textLine.equals("q")) {
					System.exit(0);
				} else if (textLine.equals("wq")) {
					bot.writeQuit();
					System.exit(0);
				}
				else if(textLine.equals("unfinished interview"))
					return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
				else if(textLine.equals("show all interviews"))
					return reponse="Your interviews:";
				else if(textLine.equals("bot interview")){
					/*List<ExpertInterview> list = new ArrayList<ExpertInterview>();
					list=exint.showAllExpertInterview();*/
					return reponse="Your bot interview:";
				}

				
				else if(textLine.equals("schedule expert interview")){
					if(textLine.contains("unfinished interview"))
						return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
					return reponse="Happy to help take your expert appointment ^^\nNow to schedule an interview please type (at a time):\n(interview date yy-mm-dd)";
				}
				else if(textLine.contains("interview date")){
					if(textLine.equals("unfinished interview"))
						return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
					return reponse="now please select the experts field, type:\n(expert field ...)";
				}
				else if(textLine.contains("expert field")){
					if(textLine.equals("unfinished interview"))
						return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
					return reponse="now please select the interview type, write:\n(interview type ...)";
				}
				else if(textLine.contains("interview type")){
					if(textLine.equals("unfinished interview"))
						return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
					return reponse="Finish By confirming the interview.\nOur best rated expert available of your chosen field\nwill automatically be affected to you,"
							+ " Write:\n(confirm interview)";
				}else if(textLine.contains("confirm interview")){
					if(textLine.equals("unfinished interview"))
						return reponse="Please finish taking your appointment before taking a new one.(type show interviews)";
					return reponse="good";
				}
				
				
				else {
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println(
								"STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0)
										+ ":TOPIC=" + chatSession.predicates.get("topic"));
					String response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						response = response.replace("&lt;", "<");
					while (response.contains("&gt;"))
						response = response.replace("&gt;", ">");
						String reponse=response;
					System.out.println("Robot : " + response);
					return reponse;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse;
		
		}
	
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		//System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
	

}
import java.model.Card

import java.util.regex.Matcher
import java.util.regex.Pattern


FileReader fr = new FileReader("res/cards.db");
BufferedReader br = new BufferedReader(fr)
def numAndName = [:];
def cards = [];
def levs = ['1':[],'2':[],'3':[],'4':[],'5':[],'6':[]];
String line = br.readLine();
Pattern levPattern = Pattern.compile("(.*)等级 * *(\\d) *编号 *：* *(\\d+).*");
Pattern valPattern = Pattern.compile("值积 *(\\d+) *代价 *(\\d+) *攻击 *(\\d+) *防御 *(\\d+)");
Pattern maiPattern = Pattern.compile(".*主卡效果 *：* *。* *(.*)");
Pattern secPattern = Pattern.compile(".*副卡效果 *：* *。* *(.*)");
Pattern covPattern = Pattern.compile(".*覆盖效果 *：* *。* *(.*)");
def card;
Integer number = 0;
while(line!=null){
	Matcher matcher = levPattern.matcher(line);
	if(matcher.find()){
		name = matcher.group(1);
		num = matcher.group(3)
		lev = matcher.group(2).trim();

		if(name!=null&&num!=null){
			card = new Card();
			cards.add(card);
			card.setName(name);
			card.setLevel(lev);
			card.setId(num);
		}
		line = br.readLine()
		continue;
	}
	matcher = valPattern.matcher(line);
	if(matcher.find()){
		val = matcher.group(1);
		cost = matcher.group(2);
		atk = matcher.group(3);
		defend = matcher.group(4);
		card.setValue(val as int);
		card.setCost(cost as int)
		card.setAtk(atk as int)
		card.setDef(defend as int)
		line = br.readLine()
		continue;
	}
	matcher = maiPattern.matcher(line);
	if(matcher.find()){
		card.setMainD(matcher.group(1));
		line = br.readLine()
		continue;
	}
	matcher = secPattern.matcher(line);
	if(matcher.find()){
		card.setSecD(matcher.group(1));
		line = br.readLine()
		continue;
	}
	matcher = covPattern.matcher(line);
	if(matcher.find()){
		card.setCovD(matcher.group(1));
		line = br.readLine()
		continue;
	}
	line = br.readLine()
}

def random = new Random()
int i = 0;
println(cards.size())
while(i++<15){
	b = random.nextInt(cards.size());
	def cardB;
	cardB = cards.get(b);

	println(cardB)
	Thread.sleep(5000);
}

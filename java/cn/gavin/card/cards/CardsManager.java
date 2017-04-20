package cn.gavin.card.cards;

import android.content.Context;
import cn.gavin.card.db.DbHelper;
import cn.gavin.card.model.Card;
import cn.gavin.card.model.Group.CardStack;
import cn.gavin.card.utils.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gluo on 4/19/2017.
 */
public class CardsManager {
    private DbHelper dbHelper;
    private Context context;
    private CardBuilder cardBuilder;

    public CardsManager(Context context) {
        this.context = context;
        dbHelper = DbHelper.instance();
        cardBuilder = new CardBuilder();
    }

    public CardStack loadCardStack(String name) {
        CardStack cards = new CardStack();
        File file = FileUtils.getCardStackFile(name);
        if (file != null && file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String id = reader.readLine();
                while (id != null) {
                    Card card = cardBuilder.buildCard(id);
                    if (card != null) {
                        cards.add(card);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cards;
    }

    public List<String> allCardStacks() {
        List<String> stacks = new ArrayList<>();
        File director = FileUtils.getCardStackDirectory();
        if (director.exists()) {
            for (String name : director.list()) {
                stacks.add(name);
            }
        }
        return stacks;
    }

    public void saveCardStack(CardStack stack, String name) {
        File stackFile = FileUtils.getCardStackFile(name);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(stackFile, false));
            for (Card card : stack) {
                writer.write(card.getName());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addCard(String id, String tt) {
        dbHelper.insertCard(id, tt);
    }

    public void deleteCard(String id){
        dbHelper.deleteCard(id);
    }

}

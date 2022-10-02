package Snowpunk.cards.assemble.cores;

import Snowpunk.cards.assemble.CoreCard;
import Snowpunk.patches.NegativeCostFieldPatches;
import Snowpunk.patches.SCostFieldPatches;
import Snowpunk.util.UpgradeRunnable;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

import static Snowpunk.SnowpunkMod.makeID;

@NoPools
@NoCompendium
public class ExhaustCore extends CoreCard {
    public static final String ID = makeID(ExhaustCore.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = -1;

    public ExhaustCore() {
        super(ID, COST, TYPE, EffectTag.MOD);
        exhaust = true;
        NegativeCostFieldPatches.NegativeCostField.isNegativeCost.set(this, true);
    }

    @Override
    public boolean getCustomCANTSpawnCondition(ArrayList<CoreCard> coreCards) {
        int totalCost = 0;
        for (CoreCard core : coreCards) {
            totalCost += core.cost;
        }
        return totalCost < 1;
    }

    @Override
    public void setStats(AbstractCard card) {
        card.exhaust = true;
    }
}

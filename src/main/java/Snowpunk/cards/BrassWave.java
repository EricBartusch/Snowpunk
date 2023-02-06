package Snowpunk.cards;

import Snowpunk.cards.abstracts.AbstractMultiUpgradeCard;
import Snowpunk.powers.SparePartsPower;
import Snowpunk.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static Snowpunk.SnowpunkMod.makeID;

public class BrassWave extends AbstractMultiUpgradeCard {
    public final static String ID = makeID(BrassWave.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = -1;
    private static final int DMG = 4, BLOCK = 4, UP_DMG = 3, UP_BLOCK = 3, MAGIC = 1;

    public BrassWave() {
        super(ID, COST, TYPE, RARITY, TARGET);
        damage = baseDamage = DMG;
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = 0;
        info = baseInfo = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = this.energyOnUse;

        if (p.hasRelic("Chemical X")) {
            effect += ChemicalX.BOOST;
            p.getRelic("Chemical X").flash();
        }

        if (info > 0) {
            effect += info;
        }

        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                blck();
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            }
            if (magicNumber > 0)
                Wiz.applyToSelf(new SparePartsPower(p, effect));
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void addUpgrades() {
        addUpgradeData(() -> upgradeDamage(UP_DMG));
        addUpgradeData(() -> upgradeBlock(UP_BLOCK));
        addUpgradeData(() -> {
            upgradeMagicNumber(1);
            uDesc();
        });
    }
}
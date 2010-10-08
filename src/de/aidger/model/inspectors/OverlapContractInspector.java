package de.aidger.model.inspectors;

import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.view.forms.ContractEditorForm.ContractType;
import de.aidger.view.models.UIAssistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Inspector for overlapped new contracts.
 * 
 * @author aidGer Team
 */
public class OverlapContractInspector extends Inspector {

    /**
     * The contract to be checked.
     */
    Contract contract;

    /**
     * Creates an overlap contract inspector.
     * 
     * @param contract
     *            the contract to be checked
     */
    public OverlapContractInspector(Contract contract) {
        this.contract = contract;
    }

    /*
     * Checks for oberlapped new contracts.
     */
    @Override
    public void check() {
        // do nothing if contract is not new
        if (ContractType.valueOf(contract.getType()) != ContractType.newContract) {
            return;
        }

        try {
            Assistant assistant = new Assistant((new Assistant())
                .getById(contract.getAssistantId()));

            List<Contract> contracts = (new Contract()).getContracts(assistant);

            for (Contract other : contracts) {
                // only handle new contracts and different one
                if (ContractType.valueOf(other.getType()) != ContractType.newContract
                        || contract.getId().equals(other.getId())) {
                    continue;
                }

                Date s1 = contract.getStartDate();
                Date e1 = contract.getEndDate();

                Date s2 = other.getStartDate();
                Date e2 = other.getEndDate();

                if (s2.compareTo(e1) <= 0 && e2.compareTo(s1) >= 0) {
                    result = MessageFormat
                        .format(
                            _("The new contract of {0} overlaps with another one."),
                            new Object[] { new UIAssistant(assistant)
                                .toString() });

                    return;
                }
            }
        } catch (AdoHiveException e) {
        }
    }
}

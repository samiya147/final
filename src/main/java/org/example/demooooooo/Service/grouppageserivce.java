package org.example.demooooooo.Service;

import org.example.demooooooo.Entity.Detail;
import org.example.demooooooo.Entity.Expense;
import org.example.demooooooo.Entity.Expensesplit;
import org.example.demooooooo.Entity.groupdetail;
import org.example.demooooooo.Repository.addexpenserpository;
import org.example.demooooooo.Repository.grouprepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class grouppageserivce {

    private final addexpenserpository expenseRepository;
    private final grouprepository groupRepository;

    public grouppageserivce(addexpenserpository expenseRepository,
                            grouprepository groupRepository) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
    }

    public groupdetail getGroup(String groupName) {
        return groupRepository.findById(groupName)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    /* Members of group */
    public List<Detail> getMembers(String groupName) {
        return getGroup(groupName).getMembers();
    }

    /* ✅ Expenses of group */
    public List<Expense> getExpenses(String groupName) {
        groupdetail group = getGroup(groupName);
        return expenseRepository.findByGroup(group);
    }

    /* Expense amounts */
    public List<Double> getAmounts(String groupName) {
        return getExpenses(groupName)
                .stream()
                .map(Expense::getAmount)
                .toList();
    }

    public Map<String, Double> getMemberShares(String groupName) {
        Map<String, Double> shareMap = new HashMap<>();
        List<Detail> members = getMembers(groupName);

        for (Detail member : members) {
            double totalShare = getExpenses(groupName).stream()
                    .flatMap(expense -> expense.getSplits().stream())
                    .filter(split -> split.getMember().equals(member))
                    .mapToDouble(Expensesplit::getShareAmount)
                    .sum();
            shareMap.put(member.getEmail(), totalShare);
        }

        return shareMap;
    }
}
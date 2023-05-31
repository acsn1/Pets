package org.acsn1.pets.commands;

import org.acsn1.glibrary.items.ItemBuilder;
import org.acsn1.pets.Pets;
import org.acsn1.pets.object.base.BoostType;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.object.base.UCommand;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PetCommand implements UCommand {

    @Override
    public String getName() {
        return "pet";
    }

    @Override
    public String getPermission() {
        return "pet.admin";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatUtils.translateColor("&cNo permission!"));
            return;
        }

        if(args.length == 0) {
            sender.sendMessage("");
            sender.sendMessage(ChatUtils.translateColor("&6&l[ Pet Command ]"));
            sender.sendMessage(ChatUtils.translateColor("&e/pet create <name> <boost_type> &8- &fcreate a pet."));
            sender.sendMessage(ChatUtils.translateColor("&e/pet delete <name> &8- &fdelete a pet."));
            sender.sendMessage(ChatUtils.translateColor("&e/pet give <player> <type> &8- &fgive a pet to a player."));
            sender.sendMessage("");
        }

        if(args.length == 2) {
            if (args[0].equalsIgnoreCase("delete")) {
                String petName = args[1];
                Pet pet = Pets.getInstance().getPetManager().getPet(petName);
                if (pet == null) {
                    sender.sendMessage(ChatUtils.translateColor("&cPet " + petName + " does not exist."));
                    return;
                }
                pet.delete();
                sender.sendMessage(ChatUtils.translateColor("&ePet " + petName + " has been deleted."));
            } else{
                sender.sendMessage(invalidArgs);
                return;
            }
        }

            if(args.length == 3) {
                if(args[0].equalsIgnoreCase("create")) {
                    String petName = args[1];
                    BoostType boostType;
                    try{
                        boostType = BoostType.valueOf(args[2]);

                        Pet pet = Pets.getInstance().getPetManager().getPet(petName);

                        if(pet != null) {
                            sender.sendMessage(ChatUtils.translateColor("&cPet " + petName + " already exists."));
                            return;
                        }

                        Pets.getInstance().getPetManager().createPet(petName, boostType);
                        sender.sendMessage(ChatUtils.translateColor("&ePet " + petName + " has been created."));
                    } catch(Exception ex) {
                        sender.sendMessage(ChatUtils.translateColor("&cBoost type " + args[2] + " does not exist."));
                    }
            } else{
                    sender.sendMessage(invalidArgs);
                    return;
                }
        }

            if(args.length == 4) {
                if(args[0].equalsIgnoreCase("give")) {
                    Player t = Bukkit.getPlayer(args[1]);
                    if(t==null) {
                        sender.sendMessage(ChatUtils.translateColor("&c" + args[1] + " is currently offline."));
                        return;
                    }
                    String name = args[2];

                        Pet pet = Pets.getInstance().getPetManager().getPet(name);

                        if(pet==null) {
                            sender.sendMessage(ChatUtils.translateColor("&cPet " + name + " does not exist."));
                            return;
                        }

                    ItemStack egg = new ItemBuilder(pet.getMaterial(), 1)
                            .setName(pet.getItemName())
                            .setLore(pet.getLore())
                            .addEnchant(Enchantment.DURABILITY, 1)
                            .build();

                        t.getInventory().addItem(egg);
                        t.sendMessage(ChatUtils.translateColor("&eYou have been received a " + name + " pet!"));
                        sender.sendMessage(ChatUtils.translateColor("&eYou have given " + t.getName() + " a " + name + " pet!"));

                }
            }

    }


    private String invalidArgs = ChatUtils.translateColor("&cInvalid Arguments. Type \"&f/pet&c\" for help!");
}

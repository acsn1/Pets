package org.acsn1.pets.commands;

import org.acsn1.glibrary.items.ItemBuilder;
import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
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
            sender.sendMessage(ChatUtils.translateColor("&e/pet summon &8- &fto spawn your saved pet."));
            sender.sendMessage(ChatUtils.translateColor("&e/pet namechange <name> &8- &fto change your active pet's name."));
            sender.sendMessage("");
        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("summon")) {
                if(!(sender instanceof Player)) return;
                Player p = (Player) sender;
            PlayerPet ppet = Pets.getInstance().getPPetManager().getPlayerPet(p.getUniqueId());

            if(ppet != null) {
                p.sendMessage(ChatUtils.translateColor("&cYou have an active pet!"));
                return;
            }

            ppet = Pets.getInstance().getPPetManager().getPetFromFile(p.getUniqueId());
            ppet.summon(p);

            } else{
                sender.sendMessage(invalidArgs);
            }
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
            }
            else if(args[0].equalsIgnoreCase("namechange")) {

                if(!(sender instanceof Player)) return;
                Player p = (Player) sender;
                PlayerPet ppet = Pets.getInstance().getPPetManager().getPlayerPet(p.getUniqueId());
                if(ppet==null) {
                    p.sendMessage(ChatUtils.translateColor("&cYou currently do not have any active pet."));
                    return;
                }
                String petName = args[1];
                ppet.setPetName(ChatUtils.translateColor(petName));
                ppet.getEntity().setCustomName(ChatUtils.translateColor(petName));
                ppet.save();
                p.sendMessage(ChatUtils.translateColor("&eYou have changed your pet's name to&r "+petName+"&e!"));

            } else {
                sender.sendMessage(invalidArgs);
                return;
            }
        }

            if(args.length == 3) {
                if(args[0].equalsIgnoreCase("create")) {
                    String petName = args[1];
                    BoostType boostType;
                    try {
                        boostType = BoostType.valueOf(args[2]);

                        Pet pet = Pets.getInstance().getPetManager().getPet(petName);

                        if (pet != null) {
                            sender.sendMessage(ChatUtils.translateColor("&cPet " + petName + " already exists."));
                            return;
                        }

                        Pets.getInstance().getPetManager().createPet(petName, boostType);
                        sender.sendMessage(ChatUtils.translateColor("&ePet " + petName + " has been created."));
                    } catch (Exception ex) {
                        sender.sendMessage(ChatUtils.translateColor("&cBoost type " + args[2] + " does not exist."));
                    }
                } else if(args[0].equalsIgnoreCase("give")) {
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
                                .addEnchant(Enchantment.DURABILITY, 1).hideAttributes().hideEnchants()
                                .build();

                        t.getInventory().addItem(egg);
                        t.sendMessage(ChatUtils.translateColor("&eYou have been received a " + name + " pet!"));
                        sender.sendMessage(ChatUtils.translateColor("&eYou have given " + t.getName() + " a " + name + " pet!"));

                    } else{

                    sender.sendMessage(invalidArgs);
                    return;
                }
        }


    }


    private String invalidArgs = ChatUtils.translateColor("&cInvalid Arguments. Type \"&f/pet&c\" for help!");
}

# -*- mode: ruby -*-
# vi: set ft=ruby :

BOX_IMAGE = "ubuntu/trusty64"
NODE_COUNT = 2
VAGRANTFILE_API_VERSION = "2"


Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

  config.vm.define :manager do |manager_config|
    manager_config.vm.box = BOX_IMAGE
	manager_config.vm.hostname = "manager"
	manager_config.vm.network :private_network, ip: "10.0.15.11"
	manager_config.vm.provider "virtualbox" do |vb|
        vb.memory = "1000"
    end
    manager_config.vm.provision "shell", path: "bootstrap-maven.sh"
  end

  (1..NODE_COUNT).each do |i|
    config.vm.define "worker#{i}" do |node|
	  node.vm.box = BOX_IMAGE
	  node.vm.hostname = "worker#{i}"
	  node.vm.network :private_network, ip: "10.0.15.#{20+i}"
	  node.vm.provider "virtualbox" do |vb|
	    vb.memory = "1000"
	  end
    end
  end

  config.vm.define :ansible do |ansible_config|
      ansible_config.vm.box = BOX_IMAGE
      ansible_config.vm.hostname = "ansible"
      ansible_config.vm.network :private_network, ip: "10.0.15.10"
      ansible_config.vm.provider "virtualbox" do |vb|
          vb.memory = "1000"
      end
      ansible_config.vm.provision "shell", path: "bootstrap-ansible.sh"
      #ansible_config.vm.provision "ansible_local" do |ansible|
      #   ansible.playbook="common.yml"
      #end
    end
end
# Smarthouse
Starts up the virtualmachines in Vagrantfile and runs bash scripts in them(ansible, maven)
1. vagrant up
Goes into virtualmachine named ansible
2. vagrant ssh ansible
Go to the correct path, where the ansible playbooks are located
3. cd ../.., cd vagrant
Run playbook named common.yml with ansible virtualmachine to install docker to manager and workers, create docker leader and join workers to swarm.
4. ansible-playbook common.yml
5. ansible-playbook portainer.yml

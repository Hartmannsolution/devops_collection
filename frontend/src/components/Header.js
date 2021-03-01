import React from 'react';
import { NavLink } from 'react-router-dom';
import { Menu, Icon } from 'semantic-ui-react';

export default function Header() {
  return (
    <Menu color='blue' size='massive'>
      <Menu.Item as={NavLink} to='/' name='home'>
        <Icon name='home' />
        Home
      </Menu.Item>

      <Menu.Item as={NavLink} to='content3' name='content3'>
        Content 3
      </Menu.Item>
      <Menu.Item as={NavLink} to='parentNested' name='content3'>
        parentNested
      </Menu.Item>
    </Menu>
  );
}

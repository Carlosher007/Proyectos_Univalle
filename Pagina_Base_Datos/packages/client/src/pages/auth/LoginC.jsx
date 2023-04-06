/** @format */

import {
  Button,
  ButtonGroup,
  Heading,
  VStack,
  Radio,
  RadioGroup,
  Stack,
} from '@chakra-ui/react';
import { Form, Formik } from 'formik';
import { useNavigate } from 'react-router';
import TextField from '../../components/TextField';
import { formSchemaLoginT } from '../../common/index';
import { AccountContext } from '../../components/AccountContex';
import { useContext } from 'react';
import React, { useState } from 'react';
import { toast } from 'react-toastify';

const LoginC = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const { setUser } = useContext(AccountContext);

  return (
    <Formik
      initialValues={{ celular: '', contrasena: '' }}
      validationSchema={formSchemaLoginT}
      onSubmit={(values, actions) => {
        const vals = { ...values };
        actions.resetForm();

        fetch('http://localhost:8000/auth/loginC', {
          method: 'POST',
          credentials: 'include',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(vals),
        })
          .catch((err) => {
            return;
          })
          .then((res) => {
            if (!res || !res.ok || res.status >= 400) {
              return;
            }
            return res.json();
          })
          .then((data) => {
            if (!data) return;
            setUser({ ...data });
            if (data.status) {
              setError(data.status);
              toast.warning(data.status);
            } else if (data.loggedIn) {
              navigate('/dashboardC');
            }
          });
      }}
    >
      <VStack
        as={Form}
        w={{ base: '90%', md: '500px' }}
        m='auto'
        justify='center'
        h='100vh'
        spacing='1rem'
      >
        <Heading>Log In</Heading>
        <TextField
          name='celular'
          placeholder='Digite el celular'
          autoComplete='off'
          label='Celular'
        />
        <TextField
          type='password'
          name='contrasena'
          placeholder='Digite la contraseña'
          autoComplete='off'
          label='Contraseña'
        />
        <ButtonGroup pt='1rem'>
          <Button colorScheme='teal' type='submit'>
            Log In
          </Button>
          <Button onClick={() => navigate('/registroC')}>Create Account</Button>
        </ButtonGroup>
      </VStack>
    </Formik>
  );
};

export default LoginC;

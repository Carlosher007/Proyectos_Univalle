/** @format */

import { ArrowBackIcon } from '@chakra-ui/icons';
import {
  Button,
  ButtonGroup,
  Heading,
  VStack,
  Text,
  Radio,
  RadioGroup,
  Stack,
} from '@chakra-ui/react';
import { Form, Formik } from 'formik';
import { useNavigate } from 'react-router';
import TextField from '../../components/TextField';
import FileUpload from '../../components/FileUpload';
import axios from 'axios';
import React, { useState } from 'react';
import Alert from '@material-ui/lab/Alert';
import { formSchemaRegistroC } from '../../common/index';
import { AccountContext } from '../../components/AccountContex';
import { useContext } from 'react';
import { toast } from 'react-toastify';

const RegistroC = () => {
  const { setUser } = useContext(AccountContext);
  const [selectedType, setSelectedType] = useState('');
  const [file, setFile] = useState(null);

  const [error, setError] = useState(null);
  const [openAlert, setOpenAlert] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleChange = (event) => {
    setFile(event.target.files[0]);
  };

  const geocodeAddress = async (address) => {
    try {
      const response = await axios.get(
        `https://api.opencagedata.com/geocode/v1/json`,
        {
          params: {
            q: address,
            key: 'f2e69ce8afd94ae0b31889ea9e6dd61b',
            language: 'es',
            pretty: 1,
          },
        }
      );
      if (response.data.results.length === 0) {
        return { error: 'Direccion no valida' };
      }
      const { lat, lng } = response.data.results[0].geometry;
      return { lat, lng };
    } catch (err) {
      // console.error(err);
      return { error: 'Error al geolocalizar la dirección' };
    }
  };

  const handleSubmit = async (values, actions) => {
    if (!file) {
      toast.error('Debe elegir una imágen');
      return;
    }
    const direccion = values.direccion;
    if (direccion) {
      try {
        const response = await geocodeAddress(direccion);
        if (response.error) {
          actions.setFieldError('direccion  ', response.error);
          if (response.error) {
            setOpenAlert(true);
            setTimeout(() => {
              setOpenAlert(false);
            }, 3000);
            setErrorMessage(response.error);
            return;
          }
          return;
        }
        values.latitud = response.lat;
        values.longitud = response.lng;
        //aqui podrias hacer una peticion POST para guardar los datos del usuario en tu servidor
        const vals = { ...values };

        // actions.resetForm();
        if (
          selectedType === '' ||
          selectedType === undefined ||
          selectedType === null
        ) {
          toast.error('Debe seleccionar un tipo de cuenta');
          return;
        }
        if (selectedType === 1 || selectedType === '1') {
          vals.tipo = 'Credito';
        } else {
          vals.tipo = 'Debito';
        }

        const isValidNumber = /^\d+$/.test(vals.celular);
        const isValidNumber2 = /^\d+$/.test(vals.numero_cuenta);

        if (isNaN(vals.celular) || !isValidNumber) {
          toast.warning('El celular debe ser un número');
          return;
        }

        if (isNaN(vals.numero_cuenta) || !isValidNumber2) {
          toast.warning('La cuenta debe ser un número');
          return;
        }

        const formData = new FormData();
        formData.append('recibo', file);
        formData.append('nombre', vals.nombre);
        formData.append('apellido', vals.apellido);
        formData.append('email', vals.email);
        formData.append('contrasena', vals.contrasena);
        formData.append('numero_cuenta', vals.numero_cuenta);
        formData.append('direccion', vals.direccion);
        formData.append('longitud', vals.longitud);
        formData.append('latitud', vals.latitud);
        formData.append('celular', vals.celular);
        if (selectedType === 1 || selectedType === '1') {
          formData.append('tipo', 'Credito');
        } else {
          formData.append('tipo', 'Debito');
        }

        fetch('http://localhost:8000/auth/registroC', {
          method: 'POST',
          credentials: 'include',
          body: formData,
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
            } else if (data.loogedIn) {
              navigate('/dashboardC');
            }
          });
      } catch (err) {
        actions.setFieldError(
          'direccion',
          'Error al geolocalizar la dirección'
        );
      }
    }
  };

  const navigate = useNavigate();
  return (
    <Formik
      initialValues={{
        nombre: '',
        apellido: '',
        email: '',
        contrasena: '',
        recibo: '',
        tipo: '',
        numero_cuenta: '',
        direccion: '',
        longitud: '',
        latitud: '',
        celular: '',
      }}
      validationSchema={formSchemaRegistroC}
      onSubmit={handleSubmit}
    >
      <VStack
        as={Form}
        w={{ base: '90%', md: '500px' }}
        m='auto'
        justify='center'
        spacing='1rem'
      >
        <Heading>Sign Up</Heading>
        <Text as='p' color='red.500'>
          {error}
        </Text>
        <TextField
          name='nombre'
          placeholder='Digite el nombre'
          autoComplete='off'
          label='Nombre'
        />
        <TextField
          name='apellido'
          placeholder='Digite el apellido'
          autoComplete='off'
          label='Apellido'
        />
        <TextField
          name='email'
          placeholder='Digite el email'
          autoComplete='off'
          label='Email'
        />
        <TextField
          type='password'
          name='contrasena'
          placeholder='Digite la contraseña'
          autoComplete='off'
          label='Contrasena'
        />
        <div>
          <form className='w-full max-w-sm'>
            <div className='mb-5'>
              Recibo
              <input
                type='file'
                className='p-2 border rounded'
                onChange={handleChange}
                accept='image/*'
              />
            </div>
          </form>
        </div>
        <RadioGroup onChange={(value) => setSelectedType(value)}>
          <Stack spacing={5} direction='row'>
            <Radio colorScheme='red' value='1'>
              Credito
            </Radio>

            <Radio colorScheme='green' value='2'>
              Debito
            </Radio>
          </Stack>
        </RadioGroup>
        ;
        <TextField
          name='numero_cuenta'
          placeholder='Digite el numero del medio de pago'
          autoComplete='off'
          label='Numero del medio de pago'
        />
        <TextField
          name='celular'
          placeholder='Digite el celular'
          autoComplete='off'
          label='Numero de celular'
        />
        <TextField
          name='direccion'
          placeholder='Ingresa tu dirección'
          label='Dirección'
          autoComplete='off'
          // value={direccion}
          // onChange={handleChange}
        />
        {openAlert && (
          <Alert severity='error' onClose={() => setOpenAlert(false)}>
            {errorMessage}
          </Alert>
        )}
        <ButtonGroup pt='1rem'>
          <Button colorScheme='teal' type='submit'>
            Create Account
          </Button>
          <Button
            onClick={() => navigate('/loginC')}
            leftIcon={<ArrowBackIcon />}
          >
            Back
          </Button>
        </ButtonGroup>
      </VStack>
      {/* </div> */}
    </Formik>
  );
};

export default RegistroC;

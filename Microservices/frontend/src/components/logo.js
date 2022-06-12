import PropTypes from "prop-types";
import { styled } from "@mui/material/styles";

export const Logo = styled((props) => {
  const { variant, ...other } = props;

  const color = variant === "light" ? "#C1C4D6" : "#5048E5";

  /*   return (
    <svg
      width="42"
      height="42"
      viewBox="0 0 42 42"
      xmlns="http://www.w3.org/2000/svg"
      {...other}>
      <path
        fillRule="evenodd"
        clipRule="evenodd"
        d="M22.6744 4.50247L31.9038 9.66459C32.117 9.78381 32.2944 9.95738 32.4178 10.1674C32.5413 10.3775 32.6064 10.6164 32.6064 10.8597C32.6064 11.1031 32.5413 11.342 32.4178 11.5521C32.2944 11.7621 32.117 11.9357 31.9038 12.0549L22.6745 17.2172C22.0854 17.5467 21.4212 17.7198 20.7456 17.7198C20.0698 17.7198 19.4056 17.5467 18.8166 17.2172L9.5873 12.0549C9.37415 11.9357 9.1967 11.7621 9.0732 11.5521C8.94971 11.342 8.8846 11.1031 8.8846 10.8597C8.8846 10.6164 8.94971 10.3775 9.0732 10.1674C9.1967 9.95738 9.37415 9.78381 9.5873 9.66459L18.8166 4.50247C19.4056 4.17301 20.0698 4 20.7456 4C21.4212 4 22.0854 4.17301 22.6744 4.50247Z"
        fill={color}
      />
      <path
        opacity="0.7"
        d="M22.6244 9.34853L35.8422 16.7415C36.0554 16.8607 36.2328 17.0343 36.3563 17.2443C36.4798 17.4544 36.5449 17.6933 36.5449 17.9366C36.5449 18.18 36.4798 18.419 36.3563 18.629C36.2328 18.8391 36.0554 19.0126 35.8422 19.1319L22.6244 26.5248C22.0355 26.8541 21.3712 27.0272 20.6956 27.0272C20.0199 27.0272 19.3557 26.8541 18.7667 26.5248L5.54893 19.1319C5.33578 19.0126 5.15833 18.8391 5.03483 18.629C4.91133 18.419 4.84623 18.18 4.84623 17.9366C4.84623 17.6933 4.91133 17.4544 5.03483 17.2443C5.15833 17.0343 5.33578 16.8607 5.54893 16.7415L18.7667 9.34853C19.3557 9.01916 20.0199 8.84615 20.6956 8.84615C21.3712 8.84615 22.0355 9.01916 22.6244 9.34853Z"
        fill={color}
      />
      <path
        opacity="0.4"
        d="M22.9257 14.1939L41.2984 24.4703C41.5113 24.5894 41.6884 24.7626 41.8117 24.9724C41.935 25.182 42 25.4206 42 25.6636C42 25.9065 41.935 26.1451 41.8117 26.3548C41.6884 26.5645 41.5113 26.7378 41.2984 26.8568L22.9257 37.1329C22.3377 37.4618 21.6745 37.6346 21 37.6346C20.3254 37.6346 19.6623 37.4618 19.0743 37.1329L0.701542 26.8568C0.488743 26.7378 0.311581 26.5645 0.188286 26.3548C0.0649948 26.1451 0 25.9065 0 25.6636C0 25.4206 0.0649948 25.182 0.188286 24.9724C0.311581 24.7626 0.488743 24.5894 0.701542 24.4703L19.0743 14.1939C19.6623 13.8651 20.3254 13.6923 21 13.6923C21.6745 13.6923 22.3377 13.8651 22.9257 14.1939Z"
        fill={color}
      />
    </svg>
  ); */

  return (
    <svg
      //version="1.1"
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 279 279"
      //xmlns:xlink="http://www.w3.org/1999/xlink"
      //enable-background="new 0 0 279 279"
      width="80"
      height="80"
    >
      <g>
        <path
          d="m271.5,70.5v-13c0-5.522-4.145-9.5-9.667-9.5h-65.333v39.976l-56.498,38.849-56.502-38.849v-39.976h-65.667c-5.522,0-10.333,3.978-10.333,9.5v13c0,4.838 4,8.872 8,9.799v165.402c-4,0.927-8,4.961-8,9.799v13c0,5.522 4.811,10.5 10.333,10.5h244c5.522,0 9.667-4.978 9.667-10.5v-13c0-5.185-4-9.447-9-9.949v-165.102c5-0.502 9-4.764 9-9.949zm-25,93.5v82h-17v-82h17zm-33,0v82h-17v-82h17zm-33,0v82h-17v-82h17zm-33,0v82h-17v-82h17zm-33,0v82h-17v-82h17zm-83,0h17v82h-17v-82zm33,0h17v82h-17v-82z"
          fill="white"
        />
        <path
          d="m98.5,80.102l41.502,28.498 41.498-28.498v-71.407c0-5.526-3.994-8.695-9.52-8.695h-62.9c-5.527,0-10.58,3.169-10.58,8.695v71.407zm15.797-38.959l17.496-2.768 8.041-15.787 8.036,15.787 17.499,2.768-12.531,12.521 2.776,17.498-15.78-8.049-15.785,8.049 2.779-17.498-12.531-12.521z"
          fill="white"
        />
      </g>
    </svg>
  );
})``;

Logo.defaultProps = {
  variant: "primary",
};

Logo.propTypes = {
  variant: PropTypes.oneOf(["light", "primary"]),
};

<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Validator;

class AuthController extends BaseController
{
    public function signin(Request $request)
    {
        if (Auth::attempt(['email' => $request->email, 'password' => $request->password])) {
            $authUser = Auth::user();
            $user = User::where('id', '=', $authUser->id)->with('roles')->first();
            if (
                $user->roles->contains('name', "chefDepartement")
            ) {
                if ($user->roles->where('name', 'chefDepartement')->first()->pivot->status == 0) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre compte n\'est pas encore activé']);
                }else if ($user->roles->where('name', 'chefDepartement')->first()->pivot->status == 2) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre demande est refusée']);
                }
            }
            $success['token'] =  $authUser->createToken('MyAuthApp')->plainTextToken;
            $success['name'] =  $authUser->name;
            $success['user'] = $user;
            $success['isStudent'] = false;
            $success['isAdmin'] = false;
            $success['isResponsableClub'] = false;

            if ($user->roles->contains('name', "student")) {
                $success['isStudent'] = true;
            } else if ($user->roles->contains('name', "admin")) {
                $success['isAdmin'] = true;
            }
            if ($user->roles->contains('name', "responsableClub")) {
                $success['isResponsableClub'] = true;
            }

            return $this->sendResponse($success, 'User signed in');
        } else {
            return $this->sendError('Unauthorised.', ['error' => 'Merci de verifier vos identifiants']);
        }
    }
    public function signup(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required',
            'email' => 'required|email',
            'password' => 'required',
            'confirm_password' => 'required|same:password',
            'role' => 'required|exists:roles,id',
        ]);

        if ($validator->fails()) {
            return $this->sendError('Error validation', $validator->errors());
        }

        $input = $request->all();
        $input['password'] = bcrypt($input['password']);
        $user = User::create($input);
        $user->roles()->attach($request->role, ['department' => $input['department']]);

        $success['token'] =  $user->createToken('MyAuthApp')->plainTextToken;
        $success['name'] =  $user->name;
        $success['user'] = $user;

        return $this->sendResponse($success, 'User created successfully.');
    }
}

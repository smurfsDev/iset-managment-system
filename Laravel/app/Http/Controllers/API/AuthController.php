<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\DemandeCreationClub;
use App\Models\DemandeSalle;
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
            if (
                $user->roles->contains('name', "student")
            ) {
                if ($user->roles->where('name', 'student')->first()->pivot->status == 0) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre compte n\'est pas encore activé']);
                }else if ($user->roles->where('name', 'student')->first()->pivot->status == 2) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre demande est refusée']);
                }
            }
            if (
                $user->roles->contains('name', "technicien")
            ) {
                if ($user->roles->where('name', 'technicien')->first()->pivot->status == 0) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre compte n\'est pas encore activé']);
                }else if ($user->roles->where('name', 'technicien')->first()->pivot->status == 2) {
                    return $this->sendError('Unauthorised.', ['error' => 'Votre demande est refusée']);
                }
            }
            $success['token'] =  $authUser->createToken('MyAuthApp')->plainTextToken;
            $success['name'] =  $authUser->name;
            $success['user'] = $user;
            $success['isStudent'] = false;
            $success['isAdmin'] = false;
            $success['isResponsableClub'] = false;
            $success['isChefDepartement'] = false;

            if ($user->roles->contains('name', "student")) {
                $success['isStudent'] = true;
            } else if ($user->roles->contains('name', "admin")) {
                $success['isAdmin'] = true;
            }
            if ($user->roles->contains('name', "responsableClub")||DemandeCreationClub::where('responsableClubId', '=', $user->id)->where('status','=',1)->first()) {

                $success['isResponsableClub'] = true;
            }
            if ($user->roles->contains('name', "chefDepartement")) {
                $success['isChefDepartement'] = true;
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
            'email' => 'required|email|unique:users',
            'password' => 'required',
            'confirm_password' => 'required|same:password',
            'role' => 'required|exists:roles,id',
        ]);

        if ($validator->fails()) {
            // recover all validation message into string
            $messages = $validator->errors()->all();
            // concatenate all validation message into one string
            $messages = implode("\n", $messages);

            return $this->sendError('Error validation', ['error' =>$messages]);
        }


        $input = $request->all();
        $input['password'] = bcrypt($input['password']);
        $user = User::create($input);
        $user->roles()->attach($request->role, ['department' => $input['department'],'classe' => $input['classe']?$input['classe']:0]);

        $success['token'] =  $user->createToken('MyAuthApp')->plainTextToken;
        $success['name'] =  $user->name;
        $success['user'] = $user;

        return $this->sendResponse($success, 'User created successfully.');
    }
}
